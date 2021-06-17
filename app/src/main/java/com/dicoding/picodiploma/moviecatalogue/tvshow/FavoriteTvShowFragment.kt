package com.dicoding.picodiploma.moviecatalogue.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.dicoding.picodiploma.moviecatalogue.viewmodel.FavoriteTvShowViewModel
import com.dicoding.picodiploma.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModelProvider =
                ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]
            val tvsAdapter = TvShowAdapter()

            binding.progressBar.visibility = View.VISIBLE
            viewModelProvider.getTvShowFavorite().observe(viewLifecycleOwner, { tvshow ->
                binding.progressBar.visibility = View.GONE
                tvsAdapter.setTvs(tvshow)
                tvsAdapter.notifyDataSetChanged()

            })

            with(binding.rvTvs) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = tvsAdapter
            }
        }

    }

}
