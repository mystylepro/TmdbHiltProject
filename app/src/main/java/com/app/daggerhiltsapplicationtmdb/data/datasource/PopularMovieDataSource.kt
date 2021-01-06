package com.app.daggerhiltsapplicationtmdb.data.datasource

import androidx.paging.PagingSource
import com.app.daggerhiltsapplicationtmdb.data.api.ApiService
import com.app.daggerhiltsapplicationtmdb.data.model.PopularMovieModel
import javax.inject.Inject

class PopularMovieDataSource(
    private val apiKey: String,
    private val language: String,
    private val apiService: ApiService
):PagingSource<Int, PopularMovieModel.Results>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieModel.Results> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getPopularMovie(apiKey, language, currentLoadingPageKey)
            val responseData = mutableListOf<PopularMovieModel.Results>()
            val data = response.results
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}