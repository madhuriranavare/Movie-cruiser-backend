package com.stackroute.movieservice;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//import static jdk.internal.jline.internal.Log.info;

@SpringBootApplication
//@EnableSwagger2
public class MovieServiceApplication //implements ApplicationListener<ContextRefreshedEvent>,CommandLineRunner {
{
	public  static final Logger logger = LoggerFactory.getLogger(MovieServiceApplication.class);


	public static void main(String[] args) {
		logger.info("Hello Loggers");
		SpringApplication.run(MovieServiceApplication.class, args);
	}

//	@Autowired
//	MovieRepository movieRepository;
//
//	public MovieServiceApplication(MovieRepository movieRepository) {
//		this.movieRepository = movieRepository;
//	}
//
//	@Override
//	public void run(String[] args) throws Exception {
//		movieRepository.save(new Movie(3,"venom","abcd.com",2,"2018","good"));
//		movieRepository.save(new Movie(4,"stree","hindi.com",4,"2017","bad"));
//	}
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//		movieRepository.save(new Movie(1,"SuperMan","english.com",2,"2001","good"));
//		movieRepository.save(new Movie(2,"devadas","telugu.com",4,"2000","good"));
//	}

}
