package com.dicoding.picodiploma.moviecatalogue.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.databinding.ItemsTvsBinding
import com.dicoding.picodiploma.moviecatalogue.detail.DetailMovieActivity

class TvsAdapter: RecyclerView.Adapter<TvsAdapter.TvsViewHolder>() {

    private var listTvs = ArrayList<TvShowEntity>()

    fun setTvs(tvs: List<TvShowEntity>?) {
        if (tvs == null) return
        this.listTvs.clear()
        this.listTvs.addAll(tvs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvsViewHolder {
        val itemsTvsBinding = ItemsTvsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvsViewHolder(itemsTvsBinding)
    }

    override fun onBindViewHolder(holder: TvsViewHolder, position: Int) {
        val tvs = listTvs[position]
        holder.bind(tvs)
    }

    override fun getItemCount(): Int = listTvs.size

    class TvsViewHolder(private val binding: ItemsTvsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvs: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvs.title
                tvItemGenre.text =
                    if (tvs.overview.length > 120) tvs.overview.substring(0, 120) + "..."
                    else tvs.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_TVS, tvs.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvs.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }

    }
}