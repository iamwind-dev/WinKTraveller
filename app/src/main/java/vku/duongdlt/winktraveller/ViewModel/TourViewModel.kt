package vku.duongdlt.winktraveller.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
}