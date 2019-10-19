package com.example.mylibrary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {
    @GET("businesses/search")
    Call<YelpBloodBank> getBloodGivers(
            @Query("location") String location,
            @Query("term") String term
    );
}
