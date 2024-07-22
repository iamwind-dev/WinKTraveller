package vku.duongdlt.winktraveller.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vku.duongdlt.winktraveller.model.Booking

class BookingViewModel(): ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    open fun addBooking(booking: Booking) {
        CoroutineScope(Dispatchers.IO).launch {
            val bookingRef = firebaseDatabase.getReference("bookings")
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
}