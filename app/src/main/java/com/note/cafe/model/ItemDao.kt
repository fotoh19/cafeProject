package com.note.cafe.model

import android.content.ClipData
import android.media.RouteListingPreference.Item


// interface to interact with the database
interface ItemDao {

    suspend fun insert(item: ItemsModel)


    suspend fun delete(item: ItemsModel)

    suspend fun getById(id: Int): ItemsModel


    suspend fun getAll(): List<ItemsModel>


    suspend fun clearAll()
}