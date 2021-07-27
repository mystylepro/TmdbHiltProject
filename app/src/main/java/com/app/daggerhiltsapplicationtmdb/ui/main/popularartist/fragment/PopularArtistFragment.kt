package com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.daggerhiltsapplicationtmdb.BuildConfig
import com.app.daggerhiltsapplicationtmdb.databinding.FragmentNotificationsBinding
import com.app.daggerhiltsapplicationtmdb.ui.base.adapter.HeaderFooterAdapter
import com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.adapter.PopularArtistAdapter
import com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.viewmodel.PopularArtistViewModel
import com.app.daggerhiltsapplicationtmdb.utils.NetworkHelper
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PopularArtistFragment : Fragment() {

    private val popularArtistViewModel: PopularArtistViewModel by viewModels()
    private val popularArtistAdapter = PopularArtistAdapter()

    @Inject
    lateinit var networkHelper: NetworkHelper


    private val viewBinding: FragmentNotificationsBinding by lazy {
        FragmentNotificationsBinding.inflate(layoutInflater, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.popularArtistRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = popularArtistAdapter
        }

        //set observer
        setObserve(BuildConfig.API_KEY, "en-US")
    }

    private fun setObserve(apiKey: String, language: String) {

        if (networkHelper.isNetworkConnected()) {
            lifecycleScope.launch {
                popularArtistViewModel.getPopularActor(
                    apiKey, language
                ).collect {
                    popularArtistAdapter.submitData(it)
                }
            }

            popularArtistAdapter.withLoadStateHeaderAndFooter(
                footer = HeaderFooterAdapter(),
                header = HeaderFooterAdapter()
            )
        } else {
            Snackbar.make(
                viewBinding.mainPopularArtist,
                "Please check your internet connection",
                5000
            ).show()
        }
        /*     lifecycleScope.launchWhenStarted {
                 popularArtistViewModel._setPopularArtist.collect { response ->

                     when (response) {
                         is ApiResult.Success -> {
                            // response.data?.let { popularArtistAdapter.submitData(it) }

                             popularArtistAdapter.submitData( response.data!!)
                         }

                         is ApiResult.Error -> {
                             if (response.message!!.contentEquals("1"))
                                 Snackbar.make(
                                     viewBinding.mainPopularArtist,
                                     "Please check your internet connection",
                                     5000
                                 ).show()
                             else
                                 Toast.makeText(
                                     requireContext(), response.message,
                                     Toast.LENGTH_LONG
                                 ).show()
                         }

                         is ApiResult.Loading -> {

                         }
                     }

                 }
             }*/

        /*  lifecycleScope.launch {
              popularArtistViewModel.getPopularActor(
                  apiKey, language
              ).collect { popularArtistAdapter.submitData(it) }
          }*/
        /*  popularArtistAdapter.withLoadStateHeaderAndFooter(
              footer = HeaderFooterAdapter(),
              header = HeaderFooterAdapter()
          )*/
    } /*else
    {
        Snackbar.make(
            viewBinding.mainPopularArtist,
            "Please check your internet connection",
            5000
        ).show()
    }*/


    /* popularArtistViewModel.getPopularArtistList(
         apiKey,language,page
     ).observe(viewLifecycleOwner, Observer {
         it?.let { resource ->
             when (resource.status) {
                 Status.SUCCESS -> {

                     popularArtistAdapter.submitList(resource.data!!.results)
                     viewBinding.progressbarArtist.visibility = View.GONE
                 }
                 Status.ERROR -> {
                     viewBinding.progressbarArtist.visibility = View.GONE
                     resource.message?.let {
                         Toast.makeText(
                             requireContext(),
                             it,
                             Toast.LENGTH_LONG
                         ).show()
                     }

                 }
                 Status.LOADING -> {
                     viewBinding.progressbarArtist.visibility = View.VISIBLE
                 }
             }
         }
     })*/

    /*override fun onDestroyView() {
        super.onDestroyView()
        popularArtistViewModel._setPopularArtist.cancellable()
    }*/
}