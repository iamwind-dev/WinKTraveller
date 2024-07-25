package vku.duongdlt.winktraveller.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import vku.duongdlt.winktraveller.model.Booking
import vku.duongdlt.winktraveller.model.Location
import vku.duongdlt.winktraveller.model.Tour

class TourViewModel(): ViewModel() {
    fun getAllTour(listTour: (List<Tour>) -> Unit ) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val toursRef = database.getReference("tour")
        val toursList = mutableListOf<Tour>()

        toursRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val tour = snapshot.getValue(Tour::class.java)
                    if (tour != null) {
                        toursList.add(tour)}
                }
                listTour(toursList) // Invoke the callback with the list of users
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error, e.g., log the error or invoke the callback with an empty list
                listTour(emptyList())
            }
        })
    }

    fun getTourByBookingtourId(tourId: String, tourCallback: (Tour?) -> Unit) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val tourRef = database.getReference("tour") // Đảm bảo đường dẫn đúng tới node tour
        // Sử dụng một đối tượng tour thay vì danh sách
        tourRef.orderByChild("id").equalTo(tourId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Chỉ lấy tour đầu tiên (nếu có)
                    val tour = dataSnapshot.children.firstOrNull()?.getValue(Tour::class.java)
                    tourCallback(tour) // Gọi lại hàm với tour đơn lẻ
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Xử lý lỗi, ví dụ, ghi log lỗi hoặc gọi lại hàm với giá trị null
                    tourCallback(null)
                }
            })
    }


    suspend fun getImagesFromFirebase(folder: String): List<String> {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference.child(folder)
        val images = mutableListOf<String>()

        try {
            val result = storageRef.listAll().await()
            for (fileRef in result.items) {
                val url = fileRef.downloadUrl.await().toString()
                images.add(url)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return images
    }


}