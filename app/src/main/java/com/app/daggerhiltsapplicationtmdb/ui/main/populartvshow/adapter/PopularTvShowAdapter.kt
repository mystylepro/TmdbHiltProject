package com.app.daggerhiltsapplicationtmdb.ui.main.populartvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.daggerhiltsapplicationtmdb.data.model.PopularTvShowModel
import com.app.daggerhiltsapplicationtmdb.databinding.PopularTvShowListItemBinding
import com.bumptech.glide.Glide

class PopularTvShowAdapter :
    PagingDataAdapter<PopularTvShowModel.Results, PopularTvShowAdapter.MyViewHolder>(DIFF()) {
    class MyViewHolder(var viewBinding: PopularTvShowListItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(model: PopularTvShowModel.Results) {
            viewBinding.popularTvShowTitle.text = model.original_name
            viewBinding.popularTvShowDesc.text = model.overview

            Glide.with(viewBinding.popularTvShowImage.context)
                .load("https://image.tmdb.org/t/p/w500/${model.poster_path}")
                .into(viewBinding.popularTvShowImage)
        }
    }

    companion object {
        class DIFF : DiffUtil.ItemCallback<PopularTvShowModel.Results>() {
            override fun areItemsTheSame(
                oldItem: PopularTvShowModel.Results,
                newItem: PopularTvShowModel.Results,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PopularTvShowModel.Results,
                newItem: PopularTvShowModel.Results,
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            PopularTvShowListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        getItem(position)?.let { holder.bind(it) }
    }

}