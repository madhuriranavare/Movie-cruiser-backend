package com.stackroute.movieservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.support.MethodOverride;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

//    @Id
    private int id;

   // @NotBlank(message = "Name shouldn't be blank")
    //@Size(min=2, message="Name should have atleast 2 characters")
    private String movieTitle;

  //  @NotBlank(message = "posterURL shouldn't be blank")
    //@Size(min=2, message="Name should have atleast 2 characters")
    private String posterURL;



    //@Size(min=2, message="Name should have atleast 2 characters")
    private float rating;

   // @NotBlank(message = "yearOfRelease shouldn't be blank")
    //@Size(min=2, message="Name should have atleast 2 characters")
    private String yearOfRelease;

   // @NotBlank(message = "Comment shouldn't be blank")
    //@Size(min=2, message="Name should have atleast 2 characters")
    private String comment;


}
