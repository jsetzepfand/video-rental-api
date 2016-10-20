package com.casumo.videorental.service.impl;

import com.casumo.videorental.data.Movie;
import com.casumo.videorental.data.MovieQuery;
import com.casumo.videorental.data.MovieRental;
import com.casumo.videorental.data.MovieType;
import com.casumo.videorental.persistence.RentalDatabaseService;
import com.casumo.videorental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class RentalServiceImpl implements RentalService {

	private final double PREMIUM_PRICE = 40;

	private final double BASIC_PRICE = 30;

	private final String CURRENCY = "SEK";

	@Autowired
	private RentalDatabaseService databaseService;

	private static Map<Integer, Movie> movies = new HashMap<>();
	private static Map<Integer, MovieRental> rentals = new HashMap<>();
	private boolean testData = false;

	static {
		Movie mov1 = new Movie();
		mov1.setMovieId(1000);
		mov1.setMovieName("Matrix");
		mov1.setMovieType(MovieType.OLD);
		movies.put(mov1.getMovieId(), mov1);

		Movie mov2 = new Movie();
		mov2.setMovieId(2000);
		mov2.setMovieName("Spider Man");
		mov2.setMovieType(MovieType.NEW);
		movies.put(mov2.getMovieId(), mov2);

		Movie mov3 = new Movie();
		mov3.setMovieId(3000);
		mov3.setMovieName("The Big Lebowski");
		mov3.setMovieType(MovieType.REGULAR);
		movies.put(mov3.getMovieId(), mov3);
	}

	public void generateTestData() {
		if(!testData) {
			for (Movie m : movies.values()) {
				databaseService.saveMovie(m);
			}
			this.testData=true;
		}
	}

	@Override
	public List<Movie> getMovies(MovieQuery movieQuery) {
		generateTestData();
		// return movies.values().stream().collect(Collectors.toList());
		return databaseService.getMovies();
	}

	@Override
	public double getPrice(String type, int days) {
		double price = BASIC_PRICE;
		switch (MovieType.getByName(type.toLowerCase())) {
		case OLD:
			if((days - 5) <= 0) {
				return BASIC_PRICE * days;
			} else {
				return BASIC_PRICE * 5 + (days - 5) * BASIC_PRICE;
			}
		case REGULAR:
			if((days - 3) <= 0) {
				return BASIC_PRICE * days;
			} else {
				return BASIC_PRICE * 3 + (days - 3) * BASIC_PRICE;
			}
		case NEW:
			return days * PREMIUM_PRICE;
		default:
			break;
		}
		return -1;
	}

	@Override
	public void rentMovie(int movieId, int customerId, int days) {
		MovieRental re = new MovieRental();
		re.setCustomerId(customerId);
		re.setMovieId(movieId);

		Movie mov = movies.get(movieId);
		re.setPrice(getPrice(mov.getMovieType().getName(), days));

		// TODO just for testing eh
		int key = (int)Math.random();
		while(rentals.containsKey(key)) {
			key = (int)Math.random();
		}
		rentals.put(key, re);
	}

	@Override
	public void returnMovie(int movieId, int customerId) {

	}

	@Override
	public int getBonusPoints(int customer) {

		return 0;
	}
}
