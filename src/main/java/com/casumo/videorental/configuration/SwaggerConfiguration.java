package com.casumo.videorental.configuration;

import com.google.common.base.Predicate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
@Profile("development")
public class SwaggerConfiguration {

	private List<String> inclusions = new ArrayList<>();

	private List<String> exclusions = new ArrayList<>();

	public List<String> getInclusions() {
		return inclusions;
	}

	public List<String> getExclusions() {
		return exclusions;
	}

	/**
	 * Swagger overall documentation
	 *
	 * @return {@link Docket}
	 */
	@Bean
	public Docket rentalApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(mergedInclusionsAndExclusions())
			.build();
	}

	/**
	 * Swagger API info
	 *
	 * @return {@link ApiInfo}
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Movie API")
			.description("Movie API Endpoints")
			.contact("Joerg Setzepfand - Malta")
			.license("Casumo")
			.licenseUrl("http://www.casumo.com")
			.version("1.1-SNAPSHOT")
			.build();
	}

	private Predicate<String> mergedInclusionsAndExclusions() {
		Predicate<String> excludesPredicate = not(includeAllFromList(getExclusions()));
		Predicate<String> includesPredicate = includeAllFromList(getInclusions());
		return or(excludesPredicate, includesPredicate);
	}

	private Predicate includeAllFromList(List<String> predicates) {
		return or(arrayOfPredicates(predicates));
	}

	private Predicate[] arrayOfPredicates(List<String> paths) {
		Predicate[] predicates = paths.stream()
			.map(PathSelectors::regex)
			.collect(Collectors.toList())
			.toArray(new Predicate[paths.size()]);
		return predicates;
	}

}
