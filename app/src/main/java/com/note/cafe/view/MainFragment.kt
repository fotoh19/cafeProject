package com.note.cafe.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.note.cafe.R
import com.note.cafe.view.adapter.CategoryAdapter
import com.note.cafe.view.adapter.OfferAdapter
import com.note.cafe.view.adapter.PopularAdapter
import com.note.cafe.databinding.FragmentMainBinding
import com.note.cafe.viewmodel.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val viewModel = MainViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding

        initCategory()
        initPopular()
        initOffer()
        bottomMenu()

    }

    private fun bottomMenu() {
        fragmentMainBinding!!.cartBtn.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_cartFragment)
        }
    }

    private fun initOffer() {
        fragmentMainBinding!!.progressBaravailableoffers.visibility = View.VISIBLE
        viewModel.offer.observe(viewLifecycleOwner) {
            fragmentMainBinding!!.recyclerviewavailableoffers.layoutManager =
                LinearLayoutManager()
            fragmentMainBinding!!.recyclerviewavailableoffers.adapter = OfferAdapter()
            fragmentMainBinding!!.progressBaravailableoffers.visibility = View.GONE
        }
        viewModel.loadoffer()
    }

    private fun initPopular() {
        fragmentMainBinding!!.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(viewLifecycleOwner) {
            fragmentMainBinding!!.recyclerviewPopular.layoutManager =
                LinearLayoutManager()
            fragmentMainBinding!!.recyclerviewPopular.adapter = PopularAdapter()
            fragmentMainBinding!!.progressBarPopular.visibility = View.GONE
        }
        viewModel.loadPopular()
    }

    private fun LinearLayoutManager(): LinearLayoutManager {
        TODO("Not yet implemented")
    }


    private fun initCategory() {
        fragmentMainBinding!!.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(viewLifecycleOwner) {
            fragmentMainBinding!!.recyclerViewCategory.layoutManager =
                LinearLayoutManager()
            fragmentMainBinding!!.recyclerViewCategory.adapter = CategoryAdapter()
            fragmentMainBinding!!.progressBarCategory.visibility = View.GONE
        }
        viewModel.loadCategory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }
}

