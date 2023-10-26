package com.example.wallpaper.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.wallpaper.model.UserModel
import com.example.wallpaper.navigation.AuthScreen
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val TAG = "AuthViewModel"
    private val auth = FirebaseAuth.getInstance()

    var isAuthenticated by mutableStateOf(false)
    var loading by mutableStateOf(false)
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun createUserWithEmailAndPassword(userDetails: UserModel,navController: NavController) {
        loading = true
        println(userDetails.email)
        try {
            auth.createUserWithEmailAndPassword(userDetails.email, userDetails.password)
                .addOnCompleteListener { task ->
                    loading = false
                    println(task)

                    if (task.isSuccessful) {
                        isAuthenticated = true
                        navController.navigate(AuthScreen.MainNav.route)
                    } else {
                        _error.value = task.exception?.message
                    }
                }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String,navController: NavController) {
        loading = true
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    loading = false
                    if (task.isSuccessful) {
                        println("authenticated")
                        isAuthenticated = true
                        navController.navigate(AuthScreen.MainNav.route)

                    } else {
                        println(task.exception?.message)
                        _error.value = task.exception?.message
                    }
                }
        } catch (e: Exception) {
            println(e.message)
            _error.value = e.message
        }
    }

    fun signOut(navController: NavController) {
        auth.signOut()
        isAuthenticated = false
        navController.navigate(AuthScreen.Login.route) {
            popUpTo(AuthScreen.Login.route) {
                inclusive = true
            }
        }
    }


}