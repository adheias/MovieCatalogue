package com.dicoding.picodiploma.moviecatalogue.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue.databinding.FragmentTvsBinding


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
            val viewModelProvider = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvsViewModel::class.java]
            val tvs = viewModelProvider.getTvs()
            val tvsAdapter = TvsAdapter()
            tvsAdapter.setTvs(tvs)

            with(fragmentTvsBinding.rvTvs) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvsAdapter
            }
        }

    }

}