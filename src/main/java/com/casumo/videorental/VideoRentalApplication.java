package com.casumo.videorental;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = { "com.casumo.videorental" })
public class VideoRentalApplication  implements EmbeddedServletContainerCustomizer {

	@Value("${tomcat.maxThreads}")
	private int maxThreads;
	@Value("${tomcat.acceptCount}")
	private int acceptCount;
	@Value("${tomcat.connectionTimeout}")
	private int connectionTimeout;

	/**
	 * Configure the embedded servlet container with
	 * parameters from a configuration file
	 *
	 * @param container {@link ConfigurableEmbeddedServletContainer}
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		if (container instanceof TomcatEmbeddedServletContainerFactory) {
			TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
			tomcat.addConnectorCustomizers(connector -> {
				connector.setAttribute("maxThreads", maxThreads);
				connector.setAttribute("acceptCount", acceptCount);
				connector.setAttribute("connectionTimeout", connectionTimeout);
			});
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(VideoRentalApplication.class, args);
	}
}
