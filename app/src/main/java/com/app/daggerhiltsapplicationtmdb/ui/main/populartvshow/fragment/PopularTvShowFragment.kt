package com.app.daggerhiltsapplicationtmdb.ui.main.populartvshow.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.daggerhiltsapplicationtmdb.BuildConfig
import com.app.daggerhiltsapplicationtmdb.databinding.FragmentDashboardBinding
import com.app.daggerhiltsapplicationtmdb.ui.base.adapter.HeaderFooterAdapter
import com.app.daggerhiltsapplicationtmdb.ui.main.populartvshow.adapter.PopularTvShowAdapter
import com.app.daggerhiltsapplicationtmdb.ui.main.populartvshow.viewmodel.PopularTvShowViewModel
import com.app.daggerhiltsapplicationtmdb.utils.NetworkHelper
import com.app.daggerhiltsapplicationtmdb.utils.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PopularTvShowFragment : Fragment() {

    private val popularTvShowViewModel: PopularTvShowViewModel by viewModels()
    private var popularTvShowAdapter = PopularTvShowAdapter()

    @Inject
    lateinit var networkHelper: NetworkHelper
    private val viewBinding : FragmentDashboardBinding by lazy {
        FragmentDashboardBinding.inflate(layoutInflater,container,false)
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

        viewBinding.popularTvShowRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = popularTvShowAdapter
        }

        //set observer
        setObserve(BuildConfig.API_KEY,"en-US")
    }

    private fun setObserve(apiKey: String, language:String)
    {

        if (networkHelper.isNetworkConnected()) {
            lifecycleScope.launch {
                popularTvShowViewModel.getPopularTvShow(
                    apiKey,language
                ).collect { popularTvShowAdapter.submitData(it) }
            }

            popularTvShowAdapter.withLoadStateHeaderAndFooter(
                footer = HeaderFooterAdapter(),
                header = HeaderFooterAdapter()
            )
        } else {
            Snackbar.make(
                viewBinding.mainTvShow,
                "Please check your internet connection",
                5000
            ).show()
        }


        /*popularTvShowViewModel.getPopularTvShowList(
            apiKey,language,page
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        popularTvShowAdapter.submitList(resource.data!!.results)
                        viewBinding.progressBarTvShow.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        viewBinding.progressBarTvShow.visibility = View.GONE
                        resource.message?.let {
                            Toast.makeText(
                                requireContext(),
                                it,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                    Status.LOADING -> {
                        viewBinding.progressBarTvShow.visibility = View.VISIBLE
                    }
                }
            }
        })*/
    }
}