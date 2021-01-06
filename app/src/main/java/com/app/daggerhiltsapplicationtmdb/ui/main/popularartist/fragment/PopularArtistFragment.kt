package com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.daggerhiltsapplicationtmdb.BuildConfig
import com.app.daggerhiltsapplicationtmdb.R
import com.app.daggerhiltsapplicationtmdb.databinding.FragmentNotificationsBinding
import com.app.daggerhiltsapplicationtmdb.ui.base.adapter.HeaderFooterAdapter
import com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.adapter.PopularArtistAdapter
import com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.viewmodel.PopularArtistViewModel
import com.app.daggerhiltsapplicationtmdb.utils.NetworkHelper
import com.app.daggerhiltsapplicationtmdb.utils.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PopularArtistFragment : Fragment() {

    private  val popularArtistViewModel: PopularArtistViewModel by viewModels()

    @Inject
    lateinit var networkHelper: NetworkHelper

    private val viewBinding : FragmentNotificationsBinding by lazy {
        FragmentNotificationsBinding.inflate(layoutInflater,container,false)
    }

    private val popularArtistAdapter = PopularArtistAdapter()

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
        setObserve(BuildConfig.API_KEY,"en-US")
    }

    private fun setObserve(apiKey: String, language:String)
    {
        if (networkHelper.isNetworkConnected()) {
            lifecycleScope.launch {
                popularArtistViewModel.getPopularActor(
                    apiKey,language
                ).collect { popularArtistAdapter.submitData(it) }
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
    }
}