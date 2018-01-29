package com.example.elisacapololo.kiandamuzik.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Elisa Capololo on 29-01-2018.
 */

public class Client {
    public interface PopularTracks {
        @GET("popular")
        Call<List<PopularTracks>> getPopularTracks();
    }
}
