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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.R
import com.example.mtglifecounter.ui.theme.CounterViewModel
import com.example.mtglifecounter.ui.theme.CyanPastel
import com.example.mtglifecounter.ui.theme.GreenPastel
import com.example.mtglifecounter.ui.theme.PinkPastel
import com.example.mtglifecounter.ui.theme.PurplePastel

@Composable
fun CommanderScreen(
   viewModel: CounterViewModel,
    onButtonClick: () -> Unit = {}
) {
    KeepScreenOn()

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.48f)
        ) {
            Quadrant(
                sizeW = 0.5f,
                color = PinkPastel,
                rotation = 90f,
                addition = 1,
                player = viewModel.player1
            )
            Quadrant(
                sizeW = 1f,
                color = CyanPastel,
                rotation = 270f,
                addition = -1,
                player = viewModel.player3
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                ) {
                Quadrant(
                    sizeW = 0.5f,
                    color = PurplePastel,
                    rotation = 90f,
                    addition = 1,
                    player = viewModel.player2
                )
                Quadrant(
                    sizeW = 1f,
                    color = GreenPastel,
                    rotation = 270f,
                    addition = -1,
                    player = viewModel.player4
                )
            }
            Button(
                onClick = { onButtonClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally),
                shape = RectangleShape,

            ) {
                Text(text = "Game Ended")
            }
        }


    }
}
@Composable
fun Quadrant(sizeW: Float,color: Color,rotation: Float,addition: Int,player : String) {
    val number = remember { mutableIntStateOf(40) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(sizeW)
            .drawBehind
            {
                drawRect(
                    color = color,
                )
            }
    ) {
        Text(text = player)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(painter = painterResource(id = R.drawable.arrow_right_512),
                    contentDescription = "arrow",
                    contentScale = ContentScale.FillBounds,

                    modifier = Modifier
                        .clickable {
                            number.intValue -= addition
                        }
                        .size(70.dp)
                        .rotate(360f - 90f)
                        .fillMaxWidth()
                )

                Text(
                    "${number.intValue}",
                    fontSize = 60.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .rotate(rotation)
                )

                Image(painter = painterResource(id = R.drawable.arrow_right_512),
                    contentDescription = "arrow",
                    contentScale = ContentScale.FillBounds,

                    modifier = Modifier
                        .clickable {
                            number.intValue += addition
                        }
                        .size(70.dp)
                        .rotate(90f)
                        .fillMaxWidth()
                )
            }

        }
    }
}

@Composable
fun KeepScreenOn() {
    val currentView = LocalView.current
    DisposableEffect(Unit) {
        currentView.keepScreenOn = true
        onDispose {
            currentView.keepScreenOn = false
        }
    }
}
