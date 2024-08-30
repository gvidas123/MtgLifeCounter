package com.example.mtglifecounter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme
import com.example.mtglifecounter.ui.theme.YellowPastel
import com.example.mtglifecounter.ui.theme.CounterViewModel
import java.util.Calendar


@Composable
fun PlayerSelectionScreen(
    onButtonClick: () -> Unit = {},
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
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.48f)
            ) {

                Quadrant(player = "player 1",0.48f,viewModel,1)
                Box(modifier = Modifier.padding(10.dp)){}
                Quadrant(player = "player 3",0.96f,viewModel,3)

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.92f)
            ) {
                Quadrant(player = "player 2",0.48f,viewModel,2)
                Box(modifier = Modifier.padding(10.dp)){}
                Quadrant(player = "player 4",0.96f,viewModel,4)

            }
            Button(
                onClick = {
                    val currentTime = Calendar.getInstance().time
                    viewModel.startTime = currentTime
                    viewModel.startTimeMilis = System.currentTimeMillis()
                    onButtonClick()},
                shape = RectangleShape,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(text = "game start")

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Quadrant(player: String,fraction: Float, viewModel: CounterViewModel,number:Int) {
    val numbers =  arrayOf("1","2","3","4","5","6","7")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember {mutableStateOf(numbers[0])}
  
    Column {
        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
            Text(text = selectedText,modifier = Modifier
                .fillMaxWidth(fraction)
                .clickable(onClick = { expanded = true })
                .background(
                    Color.Gray
                ))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.padding(20.dp)
            ) {
                numbers.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            when(number) { // checks which quadrant it is to assign to the correct value
                                1 -> viewModel.player1 = item
                                2 -> viewModel.player2 = item
                                3 -> viewModel.player3 = item
                                4 -> viewModel.player4 = item
                            }
                            selectedText = item
                            expanded = false

                        })
                }

            }
        }
        Text(
            text = player,
            modifier = Modifier
        )
    }

}

@Preview
@Composable
fun PlayerSelectionScreenPreview() {
    MtgLifeCounterTheme {
        PlayerSelectionScreen(
            viewModel = CounterViewModel()
        )
    }
}