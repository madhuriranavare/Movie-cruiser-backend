package com.stackroute.movieservice.service;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MovieServiceTest {
    Movie movie;

    //Create a mock for UserRepository
    @Mock//test double
    MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setId(105);
        movie.setMovieTitle("Insidious");
        movie.setPosterURL("pnr.com");
        movie.setRating(2);
        movie.setYearOfRelease("2002");
        movie.setComment("bad");


    }

    @Test
    public void saveUserTestSuccess() throws MovieAlreadyExistsException{

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedUser = movieService.addMovie(movie);
        Assert.assertEquals(movie,savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveUserTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedUser = movieService.addMovie(movie);
        System.out.println("savedUser" + savedUser);
        Assert.assertEquals(movie,savedUser);
//add verify
       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }

    @Test
    public void getAllUser(){

        movieRepository.findAll();
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> userlist = movieService.viewSavedMovie();
        Assert.assertEquals(list,userlist);
        //add verify
    }

    @Test
    public void deleteMovieTestSuccess() throws MovieNotFoundException,MovieAlreadyExistsException {

        when(movieRepository.existsById(movie.getId())).thenReturn(true);

        when(movieRepository.findById(movie.getId())).thenReturn(java.util.Optional.ofNullable(movie));
        //Movie m = MovieRepository.fin
        Boolean v=movieService.deleteMovie(movie);

        Assert.assertEquals(true,v);


        //add verify
    }

    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieTestFailure() throws MovieNotFoundException,MovieAlreadyExistsException {

        when(movieRepository.existsById(movie.getId())).thenReturn(false);

        when(movieRepository.findById(movie.getId())).thenReturn(java.util.Optional.ofNullable(movie));
        //Movie m = MovieRepository.fin
        Boolean v=movieService.deleteMovie(movie);

        Assert.assertEquals(movie,v);




        //add verify
    }


    @Test
    public void updateMovieTest() throws MovieNotFoundException{
        when(movieRepository.existsById(movie.getId())).thenReturn(true);
        when(movieRepository.findById(movie.getId())).thenReturn(java.util.Optional.ofNullable(movie));

        Movie updated = movieService.updateMovie(movie);
        Assert.assertEquals(movie,updated);
    }

    @Test(expected = MovieNotFoundException.class)
    public void updateMovieTestFailure() throws MovieNotFoundException {

        when(movieRepository.existsById(movie.getId())).thenReturn(false);

        when(movieRepository.findById(movie.getId())).thenReturn(java.util.Optional.ofNullable(movie));
        //Movie m = MovieRepository.fin
        Movie v=movieService.updateMovie(movie);

        Assert.assertEquals(movie,v);
        //add verify
    }

    @Test
    public void findMovieByNameTest() throws MovieNotFoundException{
        when(movieRepository.findBymovieTitle(anyString())).thenReturn(movie);
        Movie find = movieService.findMovieByName(movie.getMovieTitle());
        Assert.assertEquals(movie,find);

    }

    @Test(expected = MovieNotFoundException.class)
    public void findMovieByNameTestFailure() throws MovieNotFoundException {

        when(movieRepository.findBymovieTitle(anyString())).thenReturn(null);

        //when(movieRepository.findById(movie.getId())).thenReturn(java.util.Optional.ofNullable(movie));
        //Movie m = MovieRepository.fin
        Movie find = movieService.findMovieByName(movie.getMovieTitle());
        //Movie v=movieService.updateMovie(movie);
        Assert.assertEquals(movie,find);
        //add verify
    }
}
