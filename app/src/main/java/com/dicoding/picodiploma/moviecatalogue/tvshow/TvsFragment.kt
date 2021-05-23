package com.dicoding.picodiploma.moviecatalogue.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue.databinding.FragmentTvsBinding
import com.dicoding.picodiploma.moviecatalogue.viewmodel.TvsViewModel
import com.dicoding.picodiploma.moviecatalogue.viewmodel.ViewModelFactory


class TvsFragment : Fragment() {

    private lateinit var fragmentTvsBinding: FragmentTvsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTvsBinding = FragmentTvsBinding.inflate(layoutInflater, container, false)
        return fragmentTvsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModelProvider = ViewModelProvider(this, factory)[TvsViewModel::class.java]
            val tvsAdapter = TvsAdapter()

            fragmentTvsBinding.progressBar.visibility = View.VISIBLE
            viewModelProvider.getTvs().observe(viewLifecycleOwner, { tvshow ->
                fragmentTvsBinding.progressBar.visibility = View.GONE
                tvsAdapter.setTvs(tvshow)
                tvsAdapter.notifyDataSetChanged()
            })

            with(fragmentTvsBinding.rvTvs) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvsAdapter
            }
        }

    }

}