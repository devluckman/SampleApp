package com.edu.filmku.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edu.filmku.databinding.ItemMovieHorizontalBinding
import com.edu.filmku.domain.model.ItemMovieModel
import com.edu.filmku.domain.model.MovieDetailModel

/**
 *
 * Created by Lukmanul Hakim on  26/02/24
 * devs.lukman@gmail.com
 */
class ShowingAdapter : RecyclerView.Adapter<ShowingAdapter.ViewHolder>() {

    private val dataList = mutableListOf<ItemMovieModel>()

    class ViewHolder(private val binding: ItemMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemMovieModel) {
            binding.apply {
                tvImbd.text = data.rating
                tvMovieName.text = data.title
                Glide.with(ivPoster)
                    .load(data.poster)
                    .into(ivPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieHorizontalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    fun submit(data : List<ItemMovieModel>) {
        dataList.clear()
        dataList.addAll(data)
        notifyItemChanged(0, dataList.size)
    }
}