package com.app.daggerhiltsapplicationtmdb.data.api

import com.app.daggerhiltsapplicationtmdb.data.model.*
import retrofit2.http.*

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

    @POST("Main/Execute")
    @Headers("Content-Type: application/json", "ClientToken: 65a9937a5192490783ea4036aa5da629")
    suspend fun getTesting(
        @Body testingModel : TestingRequest
    ): TestingModel
}