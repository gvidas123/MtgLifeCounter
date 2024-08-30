package com.example.mtglifecounter.ui.theme
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Information(
    val player1: String = ""
)


class CounterViewModel {
    private val _uiState = MutableStateFlow(Information())
    val uiState: StateFlow<Information> = _uiState.asStateFlow()
    var player1: String = "asd"
    var player2: String = "asd"
    var player3: String = "asd"
    var player4: String = "asd"
}