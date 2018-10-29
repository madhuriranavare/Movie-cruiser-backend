package com.stackroute.movieservice.repository;

import com.stackroute.movieservice.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer>{

    //@Query(value="select * from MOVIE m where m.MOVIETITLE=: movieTitle",nativeQuery = true)
    public Movie findBymovieTitle(String movieTitle);
}
