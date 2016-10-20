package com.casumo.videorental.controller;

import com.casumo.videorental.data.Movie;
import com.casumo.videorental.data.MovieQuery;
import com.casumo.videorental.service.RentalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentalController {

	private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

	@Autowired
	private RentalService rentalService;

	@ApiOperation(value = "Get Movies", nickname = "getMovies",
		notes = "retrieves a list of movies based on the query", response = Movie.class)
	@RequestMapping(value = "/movies", produces = "application/vnd.casumo.contract.price-v1+json",
		method = RequestMethod.POST)
	public List<Movie> getMovies(@RequestBody @ApiParam(name = "movieQuery",
		value = "The movie query", required = true) MovieQuery movieQuery) {
		logger.debug("Received call on /movies endpoint ...");
		return rentalService.getMovies(movieQuery);
	}

	@ApiOperation(value = "Get Price", nickname = "getPrice",
		notes = "retrieves the price for a rental", response = Double.class)
	@RequestMapping(value = "/price/{type}", produces = "application/vnd.casumo.contract.price-v1+json",
		method = RequestMethod.GET)
	public Double getPrice(@RequestParam int days, @PathVariable String type) {
		logger.debug("Received call on /price endpoint ...");
		return rentalService.getPrice(type, days);
	}

	@ApiOperation(value = "Rent a movie", nickname = "rentMovie",
		notes = "Rent a movie for a customer")
	@RequestMapping(value = "/rent/{movieId}/{customerId}", method = RequestMethod.GET)
	public void rentMovie(@RequestParam int days, @PathVariable int movieId,
		@PathVariable int customerId) {
		logger.debug("Received call on /rent endpoint ...");
		rentalService.rentMovie(movieId, customerId, days);
	}

	@ApiOperation(value = "Returning a movie", nickname = "returnMovie",
		notes = "Returning a movie for a customer")
	@RequestMapping(value = "/return/{movieId}/{customerId}", method = RequestMethod.GET)
	public void returnMovie(@PathVariable int movieId,
		@PathVariable int customerId) {
		logger.debug("Received call on /return endpoint ...");
		rentalService.returnMovie(movieId, customerId);
	}

	@RequestMapping(value = "/return/bonuspoints/{customerId}", method = RequestMethod.GET)
	public int getBonusPoints(int customer) {
		logger.debug("Received call on /bonuspoints endpoint ...");
		return rentalService.getBonusPoints(customer);
	}
}
