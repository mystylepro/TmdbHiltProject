package com.app.daggerhiltsapplicationtmdb.ui.main.popularartist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.daggerhiltsapplicationtmdb.data.model.PopularArtistModel
import com.app.daggerhiltsapplicationtmdb.databinding.PopularArtistListItemBinding
import com.bumptech.glide.Glide

class PopularArtistAdapter :
    PagingDataAdapter<PopularArtistModel.Results, PopularArtistAdapter.MyViewHolder>(DIFF()) {
    class MyViewHolder(var viewBinding: PopularArtistListItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(model: PopularArtistModel.Results) {
            viewBinding.popularActressName.text = model.name
            viewBinding.popularArtistRating.rating = (model.popularity / 20).toFloat()

            Glide.with(viewBinding.popularActressImage.context)
                .load("https://image.tmdb.org/t/p/w500/${model.profile_path}")
                .into(viewBinding.popularActressImage)
        }
    }

    companion object {
        class DIFF : DiffUtil.ItemCallback<PopularArtistModel.Results>() {
            override fun areItemsTheSame(
                oldItem: PopularArtistModel.Results,
                newItem: PopularArtistModel.Results,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PopularArtistModel.Results,
                newItem: PopularArtistModel.Results,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            PopularArtistListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


}