package com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.app.daggerhiltsapplicationtmdb.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularArtistViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {


    /* @Inject
     lateinit var networkHelper: NetworkHelper

     private var setPopularArtist: MutableStateFlow<ApiResult<PagingData<PopularArtistModel.Results>>> =
         MutableStateFlow(ApiResult.Loading())
     val _setPopularArtist: StateFlow<ApiResult<PagingData<PopularArtistModel.Results>>> =
         setPopularArtist*/

    fun getPopularActor(
        apiKey: String,
        language: String
    ) = Pager(PagingConfig(pageSize = 10)) {
        mainRepository.getPopularActor(apiKey, language)
    }.flow.cachedIn(viewModelScope)

    /*  private fun handleNetworkCalling(result: PagingData<PopularArtistModel.Results>):
              ApiResult<PagingData<PopularArtistModel.Results>> {

          return if (networkHelper.isNetworkConnected())
              ApiResult.Success(result)
          else
              ApiResult.Error("1")
      }*/

    /* override fun onCleared() {
         super.onCleared()

         setPopularArtist.cancellable()
     }*/
}