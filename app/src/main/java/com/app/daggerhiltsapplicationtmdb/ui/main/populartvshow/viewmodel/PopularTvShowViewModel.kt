package com.app.daggerhiltsapplicationtmdb.ui.main.populartvshow.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.app.daggerhiltsapplicationtmdb.data.repository.MainRepository
import com.app.daggerhiltsapplicationtmdb.utils.NetworkHelper
import com.app.daggerhiltsapplicationtmdb.utils.Resource
import kotlinx.coroutines.Dispatchers

class PopularTvShowViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    fun getPopularTvShow( apiKey: String,
                          language: String)
            =  Pager(PagingConfig(pageSize = 10)) {
        mainRepository.getPopularTvShow(apiKey,language)
    }.flow.cachedIn(viewModelScope)

}