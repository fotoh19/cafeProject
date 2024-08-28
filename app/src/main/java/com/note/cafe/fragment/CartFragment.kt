package com.note.cafe.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.note.cafe.R
import com.note.cafe.adapter.CartAdapter
import com.note.cafe.databinding.FragmentCartBinding
import com.note.cafe.service.ChangeNumberItemsListener
import com.note.cafe.service.ManagmentCart
import kotlin.math.roundToInt


class CartFragment : Fragment(R.layout.fragment_cart) {
    private var fragmentCartBinding: FragmentCartBinding? = null
    private lateinit var managment: ManagmentCart
    private var tax: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCartBinding.bind(view)
        fragmentCartBinding = binding


        calculateCart()
        initCartList()

    }

    private fun initCartList() {
        with(fragmentCartBinding!!) {
            cartView.layoutManager =
                LinearLayoutManager()
            cartView.adapter= CartAdapter(managment.getListCart(),object :ChangeNumberItemsListener{
                override fun onChanged() {
                    calculateCart()
                }

            })
        }
    }


    @SuppressLint("SetTextI18n")
    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0
        tax = ((managment.getTotalFee() * percentTax) * 100).roundToInt() / 100.0
        val total = ((managment.getTotalFee() + tax + delivery) * 100).roundToInt() / 100
        val itemTotal = (managment.getTotalFee() * 100).roundToInt() / 100

        with(fragmentCartBinding!!) {
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliverTxt.text = "$$delivery"

            totalTxt.text = "$$total"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCartBinding = null
    }

}

private fun LinearLayoutManager(): LinearLayoutManager {
    TODO("Not yet implemented")
}
