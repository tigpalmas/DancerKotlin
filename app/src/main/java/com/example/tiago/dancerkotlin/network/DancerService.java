package com.example.tiago.dancerkotlin.network;


import com.example.tiago.dancerkotlin.domain.Auth;
import com.example.tiago.dancerkotlin.domain.Contract;
import com.example.tiago.dancerkotlin.domain.Dancer;
import com.example.tiago.dancerkotlin.domain.Event;
import com.example.tiago.dancerkotlin.domain.Style;
import com.example.tiago.dancerkotlin.domain.Timeline;
import com.example.tiago.dancerkotlin.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tiago on 30/01/2017.
 */

public interface DancerService {

    @POST("/register")
    Call<Auth> registerUser(@Body Auth auth);

    @POST("/event/{id}/10")
    Call<List<Event>> getEvents(@Path("id") int id, @Body Timeline timeline);

    @GET("/style")
    Call<List<Style>> getStyles();

    @POST("/dancer")
    Call<Dancer> postDancer(@Body Dancer danncer);

    @GET("/dancer")
    Call<List<Dancer>> getDancers();

    @POST("/contract")
    Call<Contract> postContract(@Body Contract contract);
}

