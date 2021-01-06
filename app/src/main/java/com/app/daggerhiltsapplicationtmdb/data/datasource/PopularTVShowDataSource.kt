package com.app.daggerhiltsapplicationtmdb.data.datasource

import androidx.paging.PagingSource
import com.app.daggerhiltsapplicationtmdb.data.api.ApiService
import com.app.daggerhiltsapplicationtmdb.data.model.PopularMovieModel
import com.app.daggerhiltsapplicationtmdb.data.model.PopularTvShowModel
import javax.inject.Inject

class PopularTVShowDataSource @Inject constructor(
    private val apiKey: String,
    private val language: String,
    private val apiService: ApiService
) :PagingSource<Int,PopularTvShowModel.Results>()
{
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularTvShowModel.Results> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getPopularTvShow(apiKey, language, currentLoadingPageKey)
            val responseData = mutableListOf<PopularTvShowModel.Results>()
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