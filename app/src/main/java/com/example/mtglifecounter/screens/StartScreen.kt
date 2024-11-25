package com.example.mtglifecounter.screens

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtglifecounter.ui.theme.DriveViewModel
import com.example.mtglifecounter.ui.theme.MtgLifeCounterTheme
import com.example.mtglifecounter.ui.theme.Greenish
import com.example.mtglifecounter.ui.theme.LightGreenish
import com.example.mtglifecounter.ui.theme.RC_SIGN_IN
import com.google.api.services.drive.DriveScopes


@Composable
fun StartScreen(
    driveViewModel: DriveViewModel,
    activity: ComponentActivity,
    onButtonClick: () -> Unit = {},
    onSecondClick: () -> Unit = {},
) {
   val account by driveViewModel.currentAccount.observeAsState()
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
                )
            )
            {
                Text(text = "Commander",
                    color = Color.White
                )
            }
            OutlinedButton(
                modifier = Modifier.
                fillMaxWidth(),
                onClick = onSecondClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Greenish
                ),
            )
            {
                Text(
                    text = "log in check",
                    color = Color.White,

                )
            }
            OutlinedButton(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Greenish
                ),
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "sign in",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White
                )
            }

            OutlinedButton(
                onClick = onSecondClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Greenish
                ),
                modifier = Modifier.fillMaxWidth()
            ){
                if (account != null) {
                    Text(text = "Logged in as: ${account?.email}")
                } else {
                    Text(text = "Not logged in")
                }
            }



        }
    }
}
/*
@Preview
@Composable
fun StartScreenPreview(){
    MtgLifeCounterTheme{
        StartScreen(
            eViewModel)
        )
    }

}
*/
