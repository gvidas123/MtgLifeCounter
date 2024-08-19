package com.example.mtglifecounter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme

@Composable
fun StartScreen(
    onButtonClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onButtonClick
        )
        {
            Text(text = "Commander")
        }
    }
}
@Preview
@Composable
fun StartScreenPreview(){
    MtgLifeCounterTheme{
        StartScreen()
    }

}
