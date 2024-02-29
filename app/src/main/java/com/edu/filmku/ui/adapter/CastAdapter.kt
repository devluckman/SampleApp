package com.edu.filmku.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.edu.filmku.databinding.ItemCastBinding
import com.edu.filmku.databinding.ItemGenreMovieBinding
import com.edu.filmku.domain.model.CastMovieData

/**
 *
 * Created by Lukmanul Hakim on  29/02/24
 * devs.lukman@gmail.com
 */
class CastAdapter: RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private val dataList = mutableListOf<CastMovieData>()

    class ViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CastMovieData) {
            binding.apply {
                tvMovieName.text = data.name
                Glide.with(ivPictureCast)
                    .load(data.picture)
                    .transform(CenterCrop(), RoundedCorners(24))
                    .into(ivPictureCast)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCastBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    fun submit(data : List<CastMovieData>) {
        dataList.clear()
        dataList.addAll(data)
        notifyItemChanged(0, dataList.size)
    }
}