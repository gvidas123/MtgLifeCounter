package com.example.mtglifecounter

import android.os.Bundle
import android.util.MutableInt
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MtgLifeCounterTheme {
                Column( modifier = Modifier.fillMaxSize()) {
                    Row ( modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {
                        quadrant(
                            name = "top_left",
                            sizeW = 0.5f,
                            color = Color.Green,
                            rotation = 90f,
                            addtion = 1
                        )
                        quadrant(
                            name = "top_right",
                            sizeW = 1f,
                            color = Color.Red,
                            rotation = 270f,
                            addtion = -1
                        )
                    }
                    Row ( modifier = Modifier.fillMaxWidth()) {
                        quadrant(
                            name = "bot_left",
                            sizeW = 0.5f,
                            color = Color.Blue,
                            rotation = 90f,
                            addtion = 1
                        )
                        quadrant(
                            name = "bot_right",
                            sizeW = 1f,
                            color = Color.Magenta,
                            rotation = 270f,
                            addtion = -1
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun quadrant(name: String,sizeW: Float,color: Color,rotation: Float,addtion: Int) {
    var number = remember {mutableStateOf(40)}
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
                    number.value -= addtion
                }
                .size(50.dp)
                .rotate(360f - 90f)
                .fillMaxWidth()
        )

        Text("${number.value}",
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
                    number.value += addtion
                }
                .size(50.dp)
                .rotate(90f)
                .fillMaxWidth()
            )//testing
    }

}

