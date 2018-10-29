package com.stackroute.movieservice.repository;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.service.MovieService;
import com.stackroute.movieservice.service.MovieServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp()
    {
        movie = new Movie();
        movie.setId(101);
        movie.setMovieTitle("Aquaman");
        movie.setPosterURL("abc.com");
        movie.setRating(2);
        movie.setYearOfRelease("2018");
        movie.setComment("good");

    }

//    @After
//    public void tearDown(){
//
//        userRepository.deleteAll();
//    }


    @Test
    public void testSaveUser(){
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getId()).get();
        Assert.assertEquals(101,fetchMovie.getId());

    }

    @Test
    public void testSaveUserFailure(){
        Movie testMovie = new Movie(101,"Aquaman","abc.com",2,"2018","good");
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getId()).get();
        Assert.assertNotSame(movie,fetchMovie);
    }

    @Test
    public void testGetAllUser(){
        Movie u = new Movie(102,"Aquaman","abc.com",2,"2018","good");
        Movie u1 = new Movie(103,"Venom","pqr.com",2,"2018","good");
        movieRepository.save(u);
        movieRepository.save(u1);
        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Aquaman",list.get(0).getMovieTitle());
    }

    @Test
    public void testdeleteMovies(){
        Movie u = new Movie(102,"Aquaman","abc.com",2,"2018","good");
        Movie u1 = new Movie(103,"Venom","pqr.com",2,"2018","good");
        movieRepository.save(u);
        movieRepository.save(u1);
        movieRepository.delete(movie);
        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Venom",list.get(1).getMovieTitle());
    }
    @Test
    public void testFindByNameTest(){
        Movie new_movie = new Movie(104,"hey baby","abc.com",2,"2018","good");
        movieRepository.save(new_movie);
        Movie fetch = movieRepository.findBymovieTitle("hey baby");
        Assert.assertEquals("hey baby",fetch.getMovieTitle());
    }

}
