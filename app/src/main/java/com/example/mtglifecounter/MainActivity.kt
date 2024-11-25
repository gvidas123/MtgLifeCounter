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
import com.example.mtglifecounter.ui.theme.RC_SIGN_IN
import com.google.api.services.drive.Drive
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        setContent {
            MtgLifeCounterTheme {
                MtgLifeCounterApp(
                    counterViewModel = CounterViewModel(),
                    driveViewModel = DriveViewModel(this.application),
                    activity = this
                )
            }
        }
    }
}




