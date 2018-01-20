package com.example.tiago.dancerkotlin.utils;


import com.example.tiago.dancerkotlin.domain.ErrorModel;
import com.example.tiago.dancerkotlin.network.DancerProvider;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by tiago on 13/01/2017.
 */

public class ErrorUtils {

    public static ErrorModel parseError(Response<?> response){
        DancerProvider dancerProvider = new DancerProvider();

        Converter<ResponseBody, ErrorModel> converter = dancerProvider.getRetrofitClient().responseBodyConverter(ErrorModel.class, new Annotation[0]);
        ErrorModel errorModel;

        try{
            errorModel = converter.convert(response.errorBody());
        } catch (IOException e) {
           return  new ErrorModel();
        }

        return  errorModel;

    }
}
