package com.example.mtglifecounter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import com.example.mtglifecounter.ui.theme.CounterViewModel
import com.example.mtglifecounter.ui.theme.YellowPastel
import java.util.Calendar

@Composable
fun EndScreen(
    viewModel: CounterViewModel
) {
    Box(
       modifier = Modifier
           .fillMaxSize()
           .drawBehind {
               drawRect(
                   color = YellowPastel
               )

           }
    ){
        Column {
            val time = viewModel.startTime.toString()
            val endTime = Calendar.getInstance().time
            val playedTime = (System.currentTimeMillis() - viewModel.startTimeMilis) / 60000
            Text(text = "players: ${viewModel.player1} $${viewModel.player2} ${viewModel.player3} ${viewModel.player4}")
            Text(text = "start of game: $time")
            Text(text = "End of game: $endTime")
            Text(text = "Total playtime in minutes: $playedTime")
        }
    }
}