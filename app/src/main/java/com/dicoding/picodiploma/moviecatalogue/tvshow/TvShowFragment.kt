package com.dicoding.picodiploma.moviecatalogue.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue.databinding.FragmentTvshowBinding
import com.dicoding.picodiploma.moviecatalogue.viewmodel.TvsViewModel
import com.dicoding.picodiploma.moviecatalogue.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.moviecatalogue.vo.Status


class TvShowFragment : Fragment() {

    private lateinit var fragmentTvsBinding: FragmentTvshowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTvsBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return fragmentTvsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModelProvider = ViewModelProvider(this, factory)[TvsViewModel::class.java]
            val tvsAdapter = TvShowAdapter()

            fragmentTvsBinding.progressBar.visibility = View.VISIBLE
            viewModelProvider.getTvs().observe(viewLifecycleOwner, { tvshow ->
                if (tvshow != null) {
                    when (tvshow.status) {
                        Status.LOADING -> fragmentTvsBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTvsBinding.progressBar.visibility = View.GONE
                            tvsAdapter.setTvs(tvshow.data)
                            tvsAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentTvsBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvsBinding.rvTvs) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = tvsAdapter
            }
        }

    }

}