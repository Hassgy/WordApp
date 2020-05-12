package com.example.wordapp.net.api;

import com.example.wordapp.net.bean.Translate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TranslateApi {

    @GET("translate_a/single")
    Call<Translate> getTranslate(
            @Query("client") String client,
            @Query("dt") String dt,
            @Query("dj") int dj,
            @Query("ie") String ie,
            @Query("sl") String sl,
            @Query("tl") String tl,
            @Query("q") String q
    );
}
