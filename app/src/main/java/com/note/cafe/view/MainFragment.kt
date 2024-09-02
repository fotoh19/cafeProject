package com.note.cafe.view


import MainViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.note.cafe.R
import com.note.cafe.databinding.FragmentMainBinding
import com.note.cafe.view.adapter.CategoryAdapter
import com.note.cafe.view.adapter.OfferAdapter
import com.note.cafe.view.adapter.PopularAdapter
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModels()

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
        fragmentMainBinding!!.cartBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_cartFragment)
        }
    }

    private fun initOffer() {
        fragmentMainBinding!!.progressBaravailableoffers.visibility = View.VISIBLE
        lifecycleScope.launch {
            viewModel.offer.collect { offerList ->
                fragmentMainBinding!!.recyclerviewavailableoffers.layoutManager = LinearLayoutManager(requireContext())
                fragmentMainBinding!!.recyclerviewavailableoffers.adapter = OfferAdapter(offerList)
                fragmentMainBinding!!.progressBaravailableoffers.visibility = View.GONE
            }
        }
        viewModel.loadoffer()
    }

    private fun initPopular() {
        fragmentMainBinding!!.progressBarPopular.visibility = View.VISIBLE
        lifecycleScope.launch {
            viewModel.popular.collect { popularList ->
                fragmentMainBinding!!.recyclerviewPopular.layoutManager = LinearLayoutManager(requireContext())
                fragmentMainBinding!!.recyclerviewPopular.adapter = PopularAdapter(popularList)
                fragmentMainBinding!!.progressBarPopular.visibility = View.GONE
            }
        }
        viewModel.loadPopular()
    }

    private fun initCategory() {
        fragmentMainBinding!!.progressBarCategory.visibility = View.VISIBLE
        lifecycleScope.launch {
            viewModel.category.collect { categoryList ->
                fragmentMainBinding!!.recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext())
                fragmentMainBinding!!.recyclerViewCategory.adapter = CategoryAdapter(categoryList)
                fragmentMainBinding!!.progressBarCategory.visibility = View.GONE
            }
        }
        viewModel.loadCategory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }
}

