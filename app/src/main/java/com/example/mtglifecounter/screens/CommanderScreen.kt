package com.example.mtglifecounter.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.R
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme
import com.example.mtglifecounter.ui.theme.PinkPastel
import com.example.mtglifecounter.ui.theme.CyanPastel
import com.example.mtglifecounter.ui.theme.GreenPastel
import com.example.mtglifecounter.ui.theme.PurplePastel
import com.example.mtglifecounter.ui.theme.YellowPastel

@Composable
fun CommanderScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            Quadrant(
                name = "top_left",
                sizeW = 0.5f,
                color = PinkPastel,
                rotation = 90f,
                addtion = 1
            )
            Quadrant(
                name = "top_right",
                sizeW = 1f,
                color = CyanPastel,
                rotation = 270f,
                addtion = -1
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Quadrant(
                name = "bot_left",
                sizeW = 0.5f,
                color = PurplePastel,
                rotation = 90f,
                addtion = 1
            )
            Quadrant(
                name = "bot_right",
                sizeW = 1f,
                color = GreenPastel,
                rotation = 270f,
                addtion = -1
            )
        }
    }
}
@Composable
fun Quadrant(name: String,sizeW: Float,color: Color,rotation: Float,addtion: Int) {
    val number = remember { mutableIntStateOf(40) }
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(sizeW)
            .drawBehind {
                drawRect(
                    color = color,

                    )
            }
    )

    {
        Image(painter = painterResource(id = R.drawable.arrow_right_512),
            contentDescription = "YEYEYE",
            contentScale = ContentScale.FillBounds,

            modifier = Modifier
                .clickable {
                    number.intValue -= addtion
                }
                .size(50.dp)
                .rotate(360f - 90f)
                .fillMaxWidth()
        )

        Text("${number.intValue}",
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .rotate(rotation)
        )

        Image(painter = painterResource(id = R.drawable.arrow_right_512),
            contentDescription = "YEYEYE",
            contentScale = ContentScale.FillBounds,

            modifier = Modifier
                .clickable {
                    number.intValue += addtion
                }
                .size(50.dp)
                .rotate(90f)
                .fillMaxWidth()
        )//testing
    }
}
@Preview
@Composable
fun CommanderScreenPreview(){
    MtgLifeCounterTheme{
        CommanderScreen()
    }

}
