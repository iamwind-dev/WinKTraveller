package vku.duongdlt.winktraveller.ViewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import vku.duongdlt.winktraveller.model.User

class UserViewModel : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()

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

    fun getAllUsers(listUser: (List<User>) -> Unit) {
        val usersRef = firebaseDatabase.getReference("users")
        val usersList = mutableListOf<User>()

        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    val user = userSnapshot.getValue(User::class.java)
                    if (user != null) {
                        usersList.add(user)
                    }
                }
                listUser(usersList) // Invoke the callback with the list of users
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error, e.g., log the error or invoke the callback with an empty list
                listUser(emptyList())
            }
        })
    }

    fun getCurrentUser(callback: (User?) -> Unit) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseDatabase = FirebaseDatabase.getInstance()

        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid
            val userRef = firebaseDatabase.getReference("users").child(uid)

            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(User::class.java)
                    callback(user) // Gọi callback với dữ liệu người dùng
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Xử lý lỗi, ví dụ: ghi lại lỗi hoặc gọi callback với null
                    callback(null)
                }
            })
        } else {
            // Không có người dùng nào đang đăng nhập
            callback(null)
        }
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun registerUser(name: String, email: String, password: String): Boolean {
        return try {
            val authResult =firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            // Lấy UID của người dùng mới đăng ký
            val uid = authResult.user?.uid ?: return false
            val user = User(id = uid, user_username = name, user_password = password, user_email = email)
            // Lưu thông tin người dùng vào Firebase Realtime Database
            val databaseRef = FirebaseDatabase.getInstance().getReference("users").child(uid)
            databaseRef.setValue(user).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getCurrentUser() = firebaseAuth.currentUser
}

