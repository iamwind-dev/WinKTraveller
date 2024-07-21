package vku.duongdlt.winktraveller.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import vku.duongdlt.winktraveller.model.Location
import vku.duongdlt.winktraveller.model.User

class LocationViewModel() : ViewModel() {
    fun getAllLocation(listLocation: (List<Location>) -> Unit ) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val locationsRef = database.getReference("location")
        val locationsList = mutableListOf<Location>()

        locationsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val location = snapshot.getValue(Location::class.java)
                    if (location != null) {
                        locationsList.add(location)}
                }
                listLocation(locationsList) // Invoke the callback with the list of users
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error, e.g., log the error or invoke the callback with an empty list
                listLocation(emptyList())
            }
        })
    }
}