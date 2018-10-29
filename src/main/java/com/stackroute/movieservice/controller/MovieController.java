package com.stackroute.movieservice.controller;

import com.stackroute.movieservice.exception.MovieAlreadyExistsException;
import com.stackroute.movieservice.exception.MovieNotFoundException;
import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1")
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(MovieController.class);


     private MovieService movieService;

     ResponseEntity responseEntity;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("movie")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie){

        try {


            Movie savedMovie = movieService.addMovie(movie);
            logger.info("Inside post maapping controller to aadd movie");

             responseEntity= new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);

        }
        catch (MovieAlreadyExistsException exception){
            responseEntity=new ResponseEntity<String>("Unsuccessful creation",HttpStatus.CONFLICT);
            //logger.error(exception);
            logger.error(String.valueOf(exception));
        }

        return responseEntity;
    }

    @GetMapping("movie")
    public ResponseEntity<?> viewAllMovies(){
        List<Movie> movieList;
        movieList=  movieService.viewSavedMovie();
        logger.info("Inside Get Movies");
        ResponseEntity responseEntity = new ResponseEntity<List<Movie>>(movieList,HttpStatus.OK);
        logger.info("Getting movies");
        return responseEntity;
    }

    @PatchMapping("movie")
    public ResponseEntity<?> deleteMovie(@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try {

          boolean var=  movieService.deleteMovie(movie);
            responseEntity = new ResponseEntity( HttpStatus.OK);
            logger.info("Inside Delete Movies");
            return responseEntity;
        }
        catch (MovieNotFoundException mo){
            responseEntity=new ResponseEntity<String>("Unsuccessful Deletion",HttpStatus.CONFLICT);
            logger.error(String.valueOf(mo));
            return responseEntity;
        }
    }


    @PutMapping("movie")
    public ResponseEntity<?> updateMovie(@RequestBody @Valid Movie movie){

        ResponseEntity responseEntity;
        try {
            movieService.updateMovie(movie);
            responseEntity = new ResponseEntity(movie, HttpStatus.OK);
            logger.info("Inside update movie");
            return responseEntity;
        }
        catch (MovieNotFoundException mo){
            responseEntity=new ResponseEntity<String>("Unsuccessful creation",HttpStatus.CONFLICT);
            logger.error(String.valueOf(mo));
            return responseEntity;
        }
    }



    @GetMapping("search/{title}")
    public ResponseEntity<?>  searchByTitle(@PathVariable("title") String title) {
        ResponseEntity responseEntity;
        try {
            Movie searchedMovie = movieService.findMovieByName(title);
            responseEntity = new ResponseEntity<Movie>(searchedMovie, HttpStatus.OK);
            logger.info("Inside search title");
            return responseEntity;
        }
        catch(MovieNotFoundException mo){
            responseEntity=new ResponseEntity<String>(mo.getMessage(),HttpStatus.CONFLICT);
            logger.error(String.valueOf(mo));
            return  responseEntity;
        }
    }

}
