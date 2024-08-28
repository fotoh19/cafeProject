package com.note.cafe.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.note.cafe.Helper.ManagmentCart
import com.note.cafe.R
import com.note.cafe.view.adapter.SizeAdapter
import com.note.cafe.databinding.FragmentDetailBinding
import com.note.cafe.model.ItemsModel


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var fragmentDetailBinding: FragmentDetailBinding? = null

    private lateinit var item: ItemsModel
    private var managementCart: ManagmentCart? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        fragmentDetailBinding = binding


        bundle()
        initSizeList()

    }

    private fun initSizeList() {
        val sizeList = ArrayList<String>()
        sizeList.add("1")
        sizeList.add("2")
        sizeList.add("3")
        sizeList.add("4")
        fragmentDetailBinding!!.sizeList.adapter = SizeAdapter()
        fragmentDetailBinding!!.sizeList.layoutManager =
            LinearLayoutManager(LinearLayoutManager.HORIZONTAL, false)
        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        Glide.with(this)
            .load(colorList[0])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .into(fragmentDetailBinding!!.picMain)
    }

    private fun LinearLayoutManager(
        horizontal: Int,
        b: Boolean
    ): LinearLayoutManager {
        TODO("Not yet implemented")
    }

    @SuppressLint("SetTextI18n")
    private fun bundle() {
        fragmentDetailBinding!!.apply {

            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$" + item.price
            ratingBar.rating = item.rating.toFloat()

            addToCartBtn.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    numberItemTxt.text.toString()
                )
                managementCart!!.insertItems(item)

            }
            pluscart.setOnClickListener {
                numberItemTxt.text = (item.numberInCart + 1).toString()
                item.numberInCart++
            }
            minusCart.setOnClickListener {
                if (item.numberInCart > 0) {
                    numberItemTxt.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDetailBinding = null
    }
}
