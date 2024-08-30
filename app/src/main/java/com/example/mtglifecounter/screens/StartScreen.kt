package com.example.mtglifecounter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme
import com.example.mtglifecounter.ui.theme.Greenish
import com.example.mtglifecounter.ui.theme.Orangish
import com.example.mtglifecounter.ui.theme.Whitish
import com.example.mtglifecounter.ui.theme.LightGreenish
import java.io.File


@Composable
fun StartScreen(
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGreenish)
    ) {
        Column(
            modifier = Modifier,


            ) {
            OutlinedButton(

                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(),
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Greenish
                ),
                


            )
            {
                Text(text = "Commander",
                    color = Color.White
                )
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Greenish
                ),

            )
            {
                Text(
                    text = "yes",
                    color = Color.White
                )
            }
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