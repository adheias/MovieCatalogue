package com.dicoding.picodiploma.moviecatalogue.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.dicoding.picodiploma.moviecatalogue.viewmodel.FavoriteMovieViewModel
import com.dicoding.picodiploma.moviecatalogue.viewmodel.ViewModelFactory


class FavoriteMovieFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModelProvider =
                ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            binding.progressBar.visibility = View.VISIBLE

            viewModelProvider.getMovieFavorite().observe(viewLifecycleOwner, { movies ->
                binding.progressBar.visibility = View.GONE
                movieAdapter.setMovie(movies)
                movieAdapter.notifyDataSetChanged()

            })

            with(binding.rvMovie) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = movieAdapter
            }
        }
    }
}
