package com.example.mtglifecounter.ui.theme
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import java.util.Date


class CounterViewModel {
    private val currentTime = Calendar.getInstance().time
    var player1: String = "player1"
    var player2: String = "player2"
    var player3: String = "player3"
    var player4: String = "player4"
    var startTime: Date = currentTime
    var startTimeMilis: Long = 20
}