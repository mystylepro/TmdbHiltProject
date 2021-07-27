package com.app.daggerhiltsapplicationtmdb.ui.main.popularmovie.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.daggerhiltsapplicationtmdb.BuildConfig
import com.app.daggerhiltsapplicationtmdb.databinding.FragmentHomeBinding
import com.app.daggerhiltsapplicationtmdb.ui.base.adapter.HeaderFooterAdapter
import com.app.daggerhiltsapplicationtmdb.ui.main.popularmovie.adapter.PopularMovieAdapter
import com.app.daggerhiltsapplicationtmdb.ui.main.popularmovie.viewmodel.PopularMovieViewModel
import com.app.daggerhiltsapplicationtmdb.utils.NetworkHelper
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class PopularMovieFragment : Fragment() {

    private val popularMovieViewModel: PopularMovieViewModel by viewModels()
    private val popularMovieAdapter = PopularMovieAdapter()

    @Inject
    lateinit var networkHelper: NetworkHelper

    private val viewBinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater, container, false)
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

        viewBinding.popularMovieRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = popularMovieAdapter
        }

        setObserve(BuildConfig.API_KEY, "en-US")
    }

    private fun setObserve(apiKey: String, language: String) {

        if (networkHelper.isNetworkConnected()) {
            lifecycleScope.launchWhenStarted {
                popularMovieViewModel.getPopularMovieList(
                    apiKey, language
                ).collect { popularMovieAdapter.submitData(it) }
            }

            popularMovieAdapter.withLoadStateHeaderAndFooter(
                footer = HeaderFooterAdapter(),
                header = HeaderFooterAdapter()
            )
        } else {
            Snackbar.make(
                viewBinding.mainPopularMovieLay,
                "Please check your internet connection",
                5000
            ).show()
        }
        /*observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        resource.data!!.collect()
                        {
                            popularMovieAdapter.submitList(it)

                        }
                        viewBinding.progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        viewBinding.progressBar.visibility = View.GONE
                        resource.message?.let {
                            Toast.makeText(
                                requireContext(),
                                it,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                    Status.LOADING -> {
                        viewBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })*/
    }


}