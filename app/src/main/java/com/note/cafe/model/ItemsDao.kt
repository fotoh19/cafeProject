package com.note.cafe.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertItem(item: ItemsModel)

    @Update
     fun updateItem(item: ItemsModel)

    @Delete
     fun deleteItem(item: ItemsModel)




     fun getAllItems(): List<ItemsModel>
}