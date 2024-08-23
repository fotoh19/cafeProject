package com.note.cafe.service

import com.note.cafe.fragment.CartFragment
import com.note.cafe.fragment.DetailFragment
import com.note.cafe.model.ItemsModel


class ManagmentCart(val coontext: CartFragment) {
    constructor(coontext: DetailFragment) : this(CartFragment())

    private val tinyDB = TinyDB<Any>()

    fun insertItems(item: ItemsModel) {
        val listItem = getListCart()
        val existAlready = listItem.any { it.title == item.title }
        val index = listItem.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listItem[index].numberInCart = item.numberInCart
        } else {
            listItem.add(item)
        }
        tinyDB.putListObject("CartList", listItem)
    }

    fun getListCart(): ArrayList<ItemsModel> {
        return tinyDB.getListObject("CartList") ?: arrayListOf()
    }

    fun minusItem(
        listItems: MutableList<ItemsModel>,
        position: Int,
        listener: ChangeNumberItemsListener
    ) {
        if (listItems[position].numberInCart == 1) {
            listItems.removeAt(position)
        } else {
            listItems[position].numberInCart--
        }
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

    fun plusItem(
        listItems: MutableList<ItemsModel>,
        position: Int,
        listener: ChangeNumberItemsListener
    ) {
        listItems[position].numberInCart++
        tinyDB.putListObject("CartList", listItems)
        listener.onChanged()
    }

    fun getTotalFee(): Double {
        val listItem = getListCart()
        var fee = 0.0
        for (item in listItem) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}

private fun <Bitmap> TinyDB<Bitmap>.putListObject(bitmap: Bitmap, listItems: Bitmap) {
    TODO("Not yet implemented")
}
