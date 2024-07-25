package vku.duongdlt.winktraveller.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vku.duongdlt.winktraveller.model.Booking

class BookingViewModel(): ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    open fun addBooking(booking: Booking) {
        CoroutineScope(Dispatchers.IO).launch {
            val bookingRef = firebaseDatabase.getReference("booking")
            val newBookingKey = bookingRef.push().key
            if (newBookingKey != null) {
                bookingRef.child(newBookingKey).setValue(booking)
                    .addOnSuccessListener {
                        // Booking added successfully
                    }
                    .addOnFailureListener {
                        // Handle error
                    }
            }
        }
    }

    fun getBookingsByUserId(userId: String, bookingsCallback: (List<Booking>) -> Unit) {
        val bookingRef = firebaseDatabase.getReference("booking")
        val bookingsList = mutableListOf<Booking>()

        bookingRef.orderByChild("booking_user_id").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val booking = snapshot.getValue(Booking::class.java)
                        if (booking != null) {
                            bookingsList.add(booking)
                        }
                    }
                    bookingsCallback(bookingsList) // Invoke the callback with the list of bookings
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error, e.g., log the error or invoke the callback with an empty list
                    bookingsCallback(emptyList())
                }
            })
    }


}