package com.app.daggerhiltsapplicationtmdb.data.api

import com.app.daggerhiltsapplicationtmdb.data.model.PopularArtistModel
import com.app.daggerhiltsapplicationtmdb.data.model.PopularMovieModel
import com.app.daggerhiltsapplicationtmdb.data.model.PopularTvShowModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService
{
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey : String,
        @Query("language") language : String,
        @Query("page") page : Int
    ):PopularMovieModel

    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("api_key") apiKey : String,
        @Query("language") language : String,
        @Query("page") page : Int
    ):PopularTvShowModel

    @GET("person/popular")
    suspend fun getPopularArtist(
        @Query("api_key") apiKey : String,
        @Query("language") language : String,
        @Query("page") page : Int
    ):PopularArtistModel
}