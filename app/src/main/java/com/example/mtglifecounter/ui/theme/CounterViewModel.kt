package com.example.mtglifecounter.ui.theme
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
    var player1LIfe: Int = 40
    var player2LIfe: Int = 40
    var player3LIfe: Int = 40
    var player4LIfe: Int = 40

}

