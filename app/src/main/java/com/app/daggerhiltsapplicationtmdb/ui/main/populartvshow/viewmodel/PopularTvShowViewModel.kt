package com.app.daggerhiltsapplicationtmdb.ui.main.populartvshow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.app.daggerhiltsapplicationtmdb.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PopularTvShowViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getPopularTvShow(
        apiKey: String,
        language: String
    ) = Pager(PagingConfig(pageSize = 10)) {
        mainRepository.getPopularTvShow(apiKey, language)
    }.flow.cachedIn(viewModelScope)

}