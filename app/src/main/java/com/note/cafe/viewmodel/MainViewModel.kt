import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.note.cafe.model.CategoryModel
import com.note.cafe.model.ItemsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _category = MutableStateFlow<MutableList<CategoryModel>>(mutableListOf())
    val category: StateFlow<MutableList<CategoryModel>> = _category

    private val _popular = MutableStateFlow<MutableList<ItemsModel>>(mutableListOf())
    val popular: StateFlow<MutableList<ItemsModel>> = _popular

    private val _offer = MutableStateFlow<MutableList<ItemsModel>>(mutableListOf())
    val offer: StateFlow<MutableList<ItemsModel>> = _offer

    init {
        loadCategory()
        loadPopular()
        loadoffer()
    }

    fun loadCategory() {
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun loadPopular() {
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun loadoffer() {
        val ref = firebaseDatabase.getReference("Offers")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _offer.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
