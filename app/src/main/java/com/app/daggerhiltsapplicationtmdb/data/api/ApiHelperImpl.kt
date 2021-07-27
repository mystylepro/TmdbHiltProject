package com.app.daggerhiltsapplicationtmdb.data.api

import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularActressDataSource
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularMovieDataSource
import com.app.daggerhiltsapplicationtmdb.data.datasource.PopularTVShowDataSource
import com.app.daggerhiltsapplicationtmdb.data.model.*
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override  fun getPopularMovieApiHelper(
        apiKey: String,
        language: String): PopularMovieDataSource = PopularMovieDataSource(apiKey,language,apiService)

    override fun getPopularTvShowApiHelper(
        apiKey: String,
        language: String
    ): PopularTVShowDataSource = PopularTVShowDataSource(apiKey,language,apiService)

    override fun getPopularArtistApiHelper(
        apiKey: String,
        language: String
    ): PopularActressDataSource = PopularActressDataSource(apiKey,language,apiService)

}