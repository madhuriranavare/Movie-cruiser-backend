package com.stackroute.movieservice.configuration;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class PreloadData implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
    private MovieRepository movieRepository;

    public PreloadData(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        movieRepository.save(new Movie(3, "venom", "abcd.com", 2, "2018", "good"));
        movieRepository.save(new Movie(4, "stree", "hindi.com", 4, "2017", "bad"));

        //movieRepository.save(new Movie(4,"stree","hindi","save"));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        movieRepository.save(new Movie(1,"SuperMan","english.com",2,"2001","good"));
		movieRepository.save(new Movie(2,"devadas","telugu.com",4,"2000","good"));
    }
}
