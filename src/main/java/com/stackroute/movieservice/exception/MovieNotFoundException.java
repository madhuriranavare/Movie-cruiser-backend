package com.stackroute.movieservice.exception;

public class MovieNotFoundException extends Exception{
    @Override
    public String getMessage(){
        return "Movie is not found in database";
    }
}
