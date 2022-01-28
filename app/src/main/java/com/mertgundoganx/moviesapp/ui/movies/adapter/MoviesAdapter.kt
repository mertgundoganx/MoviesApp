package com.mertgundoganx.moviesapp.ui.movies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mertgundoganx.moviesapp.data.model.Movie
import com.mertgundoganx.moviesapp.databinding.ItemMovieBinding

class MoviesAdapter(
    private val movieList: ArrayList<Movie>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(val itemMovieBinding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(itemMovieBinding.root), View.OnClickListener {

        init {
            itemMovieBinding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movieList[position]
        holder.itemMovieBinding.movieTitle.text = currentMovie.title
        holder.itemMovieBinding.movieImage.load("https://image.tmdb.org/t/p/original/${currentMovie.poster_path}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

}