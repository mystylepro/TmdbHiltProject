
package com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.app.daggerhiltsapplicationtmdb.data.repository.MainRepository
import com.app.daggerhiltsapplicationtmdb.utils.NetworkHelper
import com.app.daggerhiltsapplicationtmdb.utils.Resource
import kotlinx.coroutines.Dispatchers

class PopularArtistViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    fun getPopularActor(apiKey: String,
                        language: String)
            =  Pager(PagingConfig(pageSize = 10)) {
        mainRepository.getPopularActor(apiKey,language)
    }.flow.cachedIn(viewModelScope)
}