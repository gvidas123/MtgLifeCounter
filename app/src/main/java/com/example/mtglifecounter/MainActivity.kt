package com.example.mtglifecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MtgLifeCounterTheme {
                MtgLifeCounterApp()
            }
        }
    }
}

