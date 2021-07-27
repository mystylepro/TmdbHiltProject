package com.app.daggerhiltsapplicationtmdb.ui.main.popularmovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.app.daggerhiltsapplicationtmdb.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    fun getPopularMovieList(
        apiKey: String,
        language: String
    ) = Pager(PagingConfig(pageSize = 10)) {
        mainRepository.getPopularMovie(apiKey, language)
    }.flow.cachedIn(viewModelScope)
}