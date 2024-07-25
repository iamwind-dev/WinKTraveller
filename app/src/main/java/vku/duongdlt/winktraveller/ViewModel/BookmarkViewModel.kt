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
import vku.duongdlt.winktraveller.model.Bookmark
import vku.duongdlt.winktraveller.model.Tour

class BookmarkViewModel(): ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    open fun addBookmark(bookmark: Bookmark, addCallback: (Boolean, String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val bookmarkRef = firebaseDatabase.getReference("bookmark")
            val newBookmarkKey = bookmarkRef.push().key
            if (newBookmarkKey != null) {
                bookmarkRef.child(newBookmarkKey).setValue(bookmark)
                    .addOnSuccessListener {
                        // Bookmark added successfully
                        addCallback(true, newBookmarkKey)
                    }
                    .addOnFailureListener {
                        // Handle error
                        addCallback(false, null)
                    }
            } else {
                addCallback(false, null)
            }
        }
    }

    fun getAllBookmark(userId: String, bookmarkCallback: (Bookmark?) -> Unit) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val tourRef = database.getReference("bookmark") // Ensure the correct path to the tour node
        tourRef.orderByChild("bookmark_user_id").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val bookmark = dataSnapshot.children.firstOrNull()?.getValue(Bookmark::class.java)
                    bookmarkCallback(bookmark) // Callback with the single tour
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error, e.g., log the error or callback with null value
                    bookmarkCallback(null)
                }
            })
    }

    fun getBookmarkByUserId(userId: String, bookmarksCallback: (List<Bookmark>) -> Unit) {
        val bookmarkRef = firebaseDatabase.getReference("bookmark")
        val bookmarksList = mutableListOf<Bookmark>()

        bookmarkRef.orderByChild("bookmark_user_id").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val bookmark = snapshot.getValue(Bookmark::class.java)
                        if (bookmark != null) {
                            bookmarksList.add(bookmark)
                        }
                    }
                    bookmarksCallback(bookmarksList) // Invoke the callback with the list of bookmarks
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error, e.g., log the error or invoke the callback with an empty list
                    bookmarksCallback(emptyList())
                }
            })
    }

    fun deleteBookmark(bookmarkId: String, deleteCallback: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val bookmarkRef = firebaseDatabase.getReference("bookmark").child(bookmarkId)
            bookmarkRef.removeValue()
                .addOnSuccessListener {
                    // Bookmark deleted successfully
                    deleteCallback(true)
                }
                .addOnFailureListener {
                    // Handle error
                    deleteCallback(false)
                }
        }
    }

    fun getBookmarkId(userId: String, bookmarkIdCallback: (String?) -> Unit) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val bookmarkRef = database.getReference("bookmark")
        bookmarkRef.orderByChild("bookmark_user_id").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val bookmarkId = dataSnapshot.children.firstOrNull()?.key
                    bookmarkIdCallback(bookmarkId) // Callback with the bookmark ID
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error, e.g., log the error or callback with null value
                    bookmarkIdCallback(null)
                }
            })
    }
    fun isTourBookmarked(userId: String, tourId: String, isBookmarkedCallback: (Boolean) -> Unit) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val bookmarkRef = database.getReference("bookmark")
        bookmarkRef.orderByChild("bookmark_user_id").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val isBookmarked = dataSnapshot.children.any {
                        it.child("bookmark_tour_id").value == tourId
                    }
                    isBookmarkedCallback(isBookmarked)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error, e.g., log the error or callback with false
                    isBookmarkedCallback(false)
                }
            })
    }
}

// Usage example

