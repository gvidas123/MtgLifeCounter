package com.example.mtglifecounter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.mtglifecounter.ui.theme.CounterViewModel
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme
import com.example.mtglifecounter.ui.theme.DriveViewModel
import com.google.api.services.drive.Drive
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private lateinit var driveViewModel: DriveViewModel
    private lateinit var signInLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        driveViewModel = ViewModelProvider(this)[DriveViewModel::class.java]

        // Initialize the sign-in launcher
        signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                driveViewModel.handleSignInResult(result.data) // Handle result in ViewModel
            } else {
                Log.e("GoogleSignIn", "Sign-in failed or canceled")
            }
        }

        setContent {
            MtgLifeCounterTheme {
                MtgLifeCounterApp(
                    counterViewModel = CounterViewModel(),
                    driveViewModel = DriveViewModel(this.application),
                    signInLauncher = signInLauncher
                )
            }
        }
    }
}




