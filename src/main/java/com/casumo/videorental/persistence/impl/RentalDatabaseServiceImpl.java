package com.casumo.videorental.persistence.impl;

import com.casumo.videorental.data.Customer;
import com.casumo.videorental.data.Movie;
import com.casumo.videorental.data.MovieRental;
import com.casumo.videorental.persistence.RentalDatabaseService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RentalDatabaseServiceImpl implements RentalDatabaseService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void saveMovie(Movie movie) {
		entityManager.persist(movie);
	}

	@Override
	public List<Movie> getMovies() {
		TypedQuery query = entityManager.createQuery("select m from Movie m", Movie.class);
		return query.getResultList();
	}

	@Override
	public Customer getCustomer(int id) {
		return null;
	}

	@Override
	public Movie getMovie(int id) {
		return null;
	}

	@Override
	public MovieRental getRental(int id) {
		return null;
	}

	@Override
	public MovieRental getRental(int customer, int movie) {
		return null;
	}

	@Override
	public List<MovieRental> getRentals(int customer) {
		return null;
	}
}
