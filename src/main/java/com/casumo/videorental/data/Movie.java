package com.casumo.videorental.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="movieId")
	private int movieId;

	@Column(name="movieName")
	private String movieName;
	@Column(name="movieType")
	private MovieType movieType;

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public MovieType getMovieType() {
		return movieType;
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Movie movie = (Movie) o;

		if (movieId != movie.movieId)
			return false;
		if (movieName != null ?
			!movieName.equals(movie.movieName) :
			movie.movieName != null)
			return false;
		if (movieType != movie.movieType)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = movieId;
		result = 31 * result + (movieName != null ? movieName.hashCode() : 0);
		result = 31 * result + (movieType != null ? movieType.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Movie{" +
			"movieId=" + movieId +
			", movieName='" + movieName + '\'' +
			", movieType=" + movieType +
			'}';
	}
}
