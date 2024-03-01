package com.edu.filmku.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edu.filmku.databinding.ItemMovieVerticalBinding
import com.edu.filmku.domain.model.DetailMovieModel

/**
 *
 * Created by Lukmanul Hakim on  01/03/24
 * devs.lukman@gmail.com
 */
class FavoriteAdapter(
    private val onClickAction :(DetailMovieModel) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val dataList = mutableListOf<DetailMovieModel>()

    class ViewHolder(private val binding: ItemMovieVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DetailMovieModel, onClickAction :(DetailMovieModel) -> Unit) {
            binding.apply {
                tvRating.text = data.rating
                tvMovieName.text = data.title
                Glide.with(ivPoster)
                    .load(data.poster)
                    .into(ivPoster)
                root.setOnClickListener {
                    onClickAction.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieVerticalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data, onClickAction)
    }

    fun submit(data : List<DetailMovieModel>) {
        dataList.clear()
        dataList.addAll(data)
        notifyItemChanged(0, dataList.size)
    }
}