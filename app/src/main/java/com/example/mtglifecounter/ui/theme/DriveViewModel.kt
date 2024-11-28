package com.example.mtglifecounter.ui.theme

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DriveViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData to store the current signed-in Google account
    private val _currentAccount = MutableStateFlow<GoogleSignInAccount?>(null)
    val currentAccount: StateFlow<GoogleSignInAccount?> = _currentAccount

    // Function to initialize and provide the GoogleSignInClient using the correct context
    fun getGoogleSignInClient(activity: Activity): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(DriveScopes.DRIVE)) // Request full Google Drive access
            .requestEmail() // Request the user's email
            .build()
        return GoogleSignIn.getClient(activity, gso)
    }

    fun getSignInIntent(activity: Activity): Intent {
        val googleSignInClient = getGoogleSignInClient(activity)
        return googleSignInClient.signInIntent
    }

    // Handle the sign-in result
    fun handleSignInResult(data: Intent?) {
        Log.d("GoogleSignIn", "Previous account: ${_currentAccount.value?.email}")

        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)
        _currentAccount.value = account
        Log.d("GoogleSignIn", "Sign-in successful: ${account.email}")
        Log.d("GoogleSignIn", "Updated account: ${_currentAccount.value?.email}")

    }

    fun getCurrentSignedInAccount(activity: Activity): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(activity)
    }

    fun signOut(activity: Activity) {
        val googleSignInClient = getGoogleSignInClient(activity)
        googleSignInClient.signOut().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _currentAccount.value = null
                Log.d("GoogleSignIn", "User signed out successfully")
            } else {
                Log.e("GoogleSignIn", "Sign-out failed", task.exception)
            }
        }
    }
    fun update() {
        val account = GoogleSignIn.getLastSignedInAccount(getApplication<Application>())
        if (account != null) {
            _currentAccount.value = account // Set the current account if already signed in
            Log.d("GoogleSignIn", "User already signed in: ${account.email}")
        }
    }


    // Initializer to check if user is already signed in
   init {
        val account = GoogleSignIn.getLastSignedInAccount(getApplication<Application>())
        if (account != null) {
            _currentAccount.value = account // Set the current account if already signed in
            Log.d("DriveViewModel", "User already signed in: ${account.email}")
        }
    }
}
