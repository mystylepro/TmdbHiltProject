package com.app.daggerhiltsapplicationtmdb.data.api

import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularActressDataSource
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularMovieDataSource
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularTVShowDataSource
import com.app.daggerhiltsapplicationtmdb.data.model.*

interface ApiHelper {
    fun getPopularMovieApiHelper(
         apiKey: String,
         language: String
    ): PopularMovieDataSource

    fun getPopularTvShowApiHelper(
        apiKey: String,
        language: String
    ): PopularTVShowDataSource

    fun getPopularArtistApiHelper(
        apiKey: String,
        language: String
    ): PopularActressDataSource
}