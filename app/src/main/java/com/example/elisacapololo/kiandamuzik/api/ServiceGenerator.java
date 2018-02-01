package com.example.elisacapololo.kiandamuzik.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Elisa Capololo on 29-01-2018.
 */

public class ServiceGenerator {
    private  static  String TEST_URL = "http://192.168.0.104:3000/";
    private static String BASE_URL = "https://kianda-simple-tracks-api.herokuapp.com/";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(TEST_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static  final Retrofit retrofit = builder.build();

    public static  <S> S createService(Class<S> sClass){
        return retrofit.create(sClass);
    }
}

