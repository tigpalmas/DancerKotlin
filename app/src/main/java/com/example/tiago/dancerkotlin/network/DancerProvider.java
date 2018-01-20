package com.example.tiago.dancerkotlin.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiago on 30/01/2017.
 */

public class DancerProvider {
    private DancerService mTService;
    private Retrofit mRetrofitClient;

    public DancerProvider(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new UTCDateTypeAdapter())
                .create();

        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ClientConfigs.REST_API_BASER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mTService = mRetrofitClient.create(DancerService.class);
    }

    public DancerService getmTService() {
        return mTService;
    }

    public Retrofit getRetrofitClient(){
        return mRetrofitClient;
    }

}
