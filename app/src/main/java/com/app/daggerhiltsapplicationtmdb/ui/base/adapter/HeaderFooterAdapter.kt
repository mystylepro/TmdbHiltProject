package com.app.daggerhiltsapplicationtmdb.ui.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.daggerhiltsapplicationtmdb.databinding.ProgressbarLayBinding

class HeaderFooterAdapter : LoadStateAdapter<HeaderFooterAdapter.ViewHolder>()
{
    class ViewHolder(var viewBinding: ProgressbarLayBinding) : RecyclerView.ViewHolder(viewBinding.root)
    {
        fun bind(loadState: LoadState)
        {
            if (loadState == LoadState.Loading)
            {
                viewBinding.mainLoader.visibility = View.VISIBLE
            }else
            {
                viewBinding.mainLoader.visibility = View.GONE

            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState)
    {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder =
        ViewHolder(ProgressbarLayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

}