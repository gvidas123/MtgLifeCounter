package com.example.mtglifecounter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mtglifecounter.ui.theme.CounterViewModel
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private const val RC_SIGN_IN = 100

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(DriveScopes.DRIVE))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            val driveService = initializeDriveService(account)
            getSpecificDriveFile(driveService,"mtg games")
        } else {
            Log.e("GoogleSignIn", "User is not signed in")
        }

        setContent {
            MtgLifeCounterTheme {
                MtgLifeCounterApp(
                    counterViewModel = CounterViewModel()
                )
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun getSpecificDriveFile(driveService: Drive, fileName: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val result = driveService.files().list()
                    .setQ("name = '$fileName'")
                    .setFields("files(id, name)")
                    .setPageSize(1) // Limit to 1 file
                    .execute()

                val file = result.files?.firstOrNull()
                if (file != null) {
                    Log.d("GoogleDrive", "File Name: ${file.name}, File ID: ${file.id}")
                } else {
                    Log.d("GoogleDrive", "File not found: $fileName")
                }
            } catch (e: Exception) {
                Log.e("GoogleDrive", "Error retrieving file", e)
            }
        }
    }

    private fun initializeDriveService(account: GoogleSignInAccount): Drive {
        val credential = GoogleAccountCredential.usingOAuth2(
            this, listOf(DriveScopes.DRIVE_FILE)
        )
        credential.selectedAccount = account.account

        return Drive.Builder(
            NetHttpTransport(),
            GsonFactory(),
            credential
        ).setApplicationName("YourAppName").build()
    }

}




