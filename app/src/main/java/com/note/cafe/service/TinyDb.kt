package com.note.cafe.service
import android.graphics.BitmapFactory
import android.os.Environment
import com.note.cafe.model.ItemDao
import com.note.cafe.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.ArrayList

class TinyDB<Bitmap>() {



    companion object {
        fun isExternalStorageWritable(): Boolean {
            return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
        }

        fun isExternalStorageReadable(): Boolean {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
        }
    }


    suspend fun getImage(path: String): android.graphics.Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                BitmapFactory.decodeFile(path)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun putImage(theFolder: String, theImageName: String, theBitmap: Bitmap): String? {
        val fullPath = setupFullPath(theFolder, theImageName) ?: return null
        return if (saveBitmap(fullPath, theBitmap)) {
            fullPath
        } else {
            null
        }
    }

    private fun saveBitmap(fullPath: String, bitmap: Bitmap): Boolean {
        TODO()
    }

    private fun setupFullPath(folder: String, imageName: String): String? {
        val directory = File(Environment.getExternalStorageDirectory(), folder)
        if (isExternalStorageReadable() && isExternalStorageWritable() && !directory.exists()) {
            if (!directory.mkdirs()) {
                return null
            }
        }
        return File(directory, imageName).absolutePath
    }




    suspend fun addItem(item: ItemDao) {
        withContext(Dispatchers.IO) {
            item.insert(ItemsModel())
        }
    }

    suspend fun deleteItem(item: ItemDao) {
        withContext(Dispatchers.IO) {
            item.delete(ItemsModel())
        }
    }

    suspend fun getAllItems(item: ItemDao): List<ItemsModel> {
        return withContext(Dispatchers.IO) {
            item.getAll()
        }
    }

    suspend fun clearItems(item: ItemDao) {
        withContext(Dispatchers.IO) {
            item.clearAll()
        }
    }

    fun getListObject(bitmap: Bitmap): ArrayList<ItemsModel>? {
        TODO("Not yet implemented")
    }
}