package com.app.daggerhiltsapplicationtmdb.ui.main.popularmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.daggerhiltsapplicationtmdb.data.model.PopularMovieModel
import com.app.daggerhiltsapplicationtmdb.databinding.PopularMovieListItemBinding
import com.bumptech.glide.Glide

class PopularMovieAdapter :
    PagingDataAdapter<PopularMovieModel.Results, PopularMovieAdapter.MyViewHolder>(DIFF()) {
    class MyViewHolder(var viewBinding: PopularMovieListItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(model: PopularMovieModel.Results) {
            viewBinding.popularMovieTitle.text = model.title
            viewBinding.popularMovieDesc.text = model.overview

            Glide.with(viewBinding.popularMovieImage.context)
                .load("https://image.tmdb.org/t/p/w500/${model.poster_path}")
                .into(viewBinding.popularMovieImage)
        }
    }


    companion object {
        class DIFF : DiffUtil.ItemCallback<PopularMovieModel.Results>() {
            override fun areItemsTheSame(
                oldItem: PopularMovieModel.Results,
                newItem: PopularMovieModel.Results,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PopularMovieModel.Results,
                newItem: PopularMovieModel.Results,
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            PopularMovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        holder.bind(getItem(position)!!)
    }

}