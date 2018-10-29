package com.stackroute.movieservice.service;


import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    public Movie addMovie(Movie movie) throws MovieAlreadyExistsException;
    public List<Movie> viewSavedMovie();
    public boolean deleteMovie(Movie movie) throws MovieNotFoundException;
    public Movie updateMovie(Movie movie) throws MovieNotFoundException;
    public Movie findMovieByName(String name) throws MovieNotFoundException;
}

