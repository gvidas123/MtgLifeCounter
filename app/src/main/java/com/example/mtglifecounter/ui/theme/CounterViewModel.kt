package com.example.mtglifecounter.ui.theme
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CounterViewModel {
    var player1: String = "player1"
    var player2: String = "player2"
    var player3: String = "player3"
    var player4: String = "player4"
}