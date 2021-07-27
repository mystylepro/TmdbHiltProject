package com.app.daggerhiltsapplicationtmdb.data.repository

import com.app.daggerhiltsapplicationtmdb.data.api.ApiHelper
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularActressDataSource
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularMovieDataSource
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularTVShowDataSource
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    fun getPopularMovie(
        apiKey: String,
        language: String
    ): PopularMovieDataSource = apiHelper.getPopularMovieApiHelper(apiKey,language)

    fun getPopularTvShow(
        apiKey: String,
        language: String
    ): PopularTVShowDataSource = apiHelper.getPopularTvShowApiHelper(apiKey,language)

    fun getPopularActor(
        apiKey: String,
        language: String
    ): PopularActressDataSource = apiHelper.getPopularArtistApiHelper(apiKey,language)

}