package com.stackroute.movieservice.service;

import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("impl2")
public class MovieServiceImpl2 implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) throws MovieAlreadyExistsException {
        ResponseEntity responseEntity;
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie;

    }

    @Override
    public List<Movie> viewSavedMovie() {
        List<Movie> viewMovie = (List<Movie>) movieRepository.findAll();
        return viewMovie;
    }

    @Override
    public boolean deleteMovie(Movie movie) throws MovieNotFoundException {
        if (movieRepository.existsById(movie.getId())) {

            movieRepository.delete(movie);
            return true;
        } else {
            throw new MovieNotFoundException();
        }
    }


    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
        if(movieRepository.existsById(movie.getId())) {
            Movie obj= movieRepository.findById(movie.getId()).get();
            obj.setComment(movie.getComment());
            movieRepository.deleteById(obj.getId());
            movieRepository.save(obj);
            return obj;
        }
        else {
            throw new MovieNotFoundException();
        }
    }

//    @Override
//    public List<Movie> findMovieByName(String name) {
//        List<Movie> moviess = new ArrayList<>();
//        for(Movie m: viewSavedMovie()) {
//            if(m.getMovieTitle().compareTo(name)==0) {
//                moviess.add(m);
//            }
//        }
//        return moviess;
//    }

    @Override
    public Movie findMovieByName(String name) throws MovieNotFoundException {
        if (movieRepository.findBymovieTitle(name) != null) {


            Movie search = movieRepository.findBymovieTitle(name);

            return search;

        } else {

            throw new MovieNotFoundException();

        }
    }
}
