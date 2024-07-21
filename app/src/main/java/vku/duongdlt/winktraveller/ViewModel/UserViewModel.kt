package vku.duongdlt.winktraveller.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import vku.duongdlt.winktraveller.model.User

class UserViewModel() : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    open fun addUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val userRef = firebaseDatabase.getReference("users")
            val newUserKey = userRef.push().key
            if (newUserKey != null) {
                userRef.child(newUserKey).setValue(user)
                    .addOnSuccessListener {
                // User added successfully
                }
                    .addOnFailureListener {
                    // Handle error
                }
            }
        }
    }

     fun getAllUsers(listUser: (List<User>) -> Unit ) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")
        val usersList = mutableListOf<User>()

         usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
             override fun onDataChange(dataSnapshot: DataSnapshot) {
                 for (userSnapshot in dataSnapshot.children) {
                     val user = userSnapshot.getValue(User::class.java)
                     if (user != null) {
                         usersList.add(user)}
                 }
                 listUser(usersList) // Invoke the callback with the list of users
             }

             override fun onCancelled(databaseError: DatabaseError) {
                 // Handle error, e.g., log the error or invoke the callback with an empty list
                 listUser(emptyList())
             }
         })
    }


}

