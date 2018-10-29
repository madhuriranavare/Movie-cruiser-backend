package com.stackroute.movieservice.exception;

public class MovieAlreadyExistsException extends Exception{
    @Override
    public String getMessage(){
        return "Movie already exists in database";
    }
}
