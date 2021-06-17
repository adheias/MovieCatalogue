package com.dicoding.picodiploma.moviecatalogue.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.databinding.ItemsMovieBinding
import com.dicoding.picodiploma.moviecatalogue.detail.DetailMovieActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MvViewHolder>() {

    private var listMovie = ArrayList<MovieEntity>()

    fun setMovie(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvViewHolder {
        val itemsMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MvViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MvViewHolder, position: Int) {
        val movies = listMovie[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovie.size

    class MvViewHolder(private val binding: ItemsMovieBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movies.title
                tvItemGenre.text =
                        if (movies.overview.length > 120) movies.overview.substring(0, 120) + "..."
                        else movies.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(movies.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }

    }
}
