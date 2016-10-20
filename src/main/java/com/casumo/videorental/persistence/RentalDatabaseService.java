package com.casumo.videorental.persistence;

import com.casumo.videorental.data.Customer;
import com.casumo.videorental.data.Movie;
import com.casumo.videorental.data.MovieRental;

import java.util.List;

public interface RentalDatabaseService {
	public void saveMovie(Movie movie);
	public List<Movie> getMovies();
	public Customer getCustomer(int id);
	public Movie getMovie(int id);
	public MovieRental getRental(int id);
	public MovieRental getRental(int customer, int movie);
	public List<MovieRental> getRentals(int customer);
}
