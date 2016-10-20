package com.casumo.videorental.service;

import com.casumo.videorental.data.Movie;
import com.casumo.videorental.data.MovieQuery;

import java.util.List;

public interface RentalService {
	List<Movie> getMovies(MovieQuery movieQuery);
	double getPrice(String type, int days);
	void rentMovie(int movieId, int customerId, int days);
	void returnMovie(int movieId, int customerId);
	int getBonusPoints(int customer);
}
