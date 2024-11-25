package com.example.mtglifecounter.ui.theme

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

const val RC_SIGN_IN = 100

class DriveViewModel(application: Application) : AndroidViewModel(application) {
    private val _currentAccount = MutableLiveData<GoogleSignInAccount?>()
    val currentAccount: LiveData<GoogleSignInAccount?> = _currentAccount

    private val _googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Scope(DriveScopes.DRIVE)) // Full access to Google Drive
                .requestEmail()
                .build()
        GoogleSignIn.getClient(getApplication(), gso)
    }

     val googleSignInClient: GoogleSignInClient
        get() = _googleSignInClient



    private val _driveService = MutableLiveData<Drive?>()
    val driveService: LiveData<Drive?> = _driveService


    fun initializeDriveService() {
        val account = GoogleSignIn.getLastSignedInAccount(getApplication<Application>())
        _currentAccount.postValue(account)

        val credential = GoogleAccountCredential.usingOAuth2(
                getApplication(),
                listOf(DriveScopes.DRIVE)
        ).apply {
            if (account != null) {
                selectedAccount = account.account
            }
        }

        _driveService.postValue(
                Drive.Builder(
                        NetHttpTransport(),
                        GsonFactory(),
                        credential
                ).setApplicationName("YourAppName").build()
        )
    }
    fun checkSignInStatus() {
        val account = GoogleSignIn.getLastSignedInAccount(getApplication<Application>())
        if (account != null) {
            Log.d("GoogleSignIn", "User is logged in: ${account.email}")
            Log.d("GoogleSignIn", "Granted Scopes: ${account.grantedScopes}")
        } else {
            Log.d("GoogleSignIn", "User is not logged in")
        }
    }
}