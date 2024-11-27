package com.example.mtglifecounter.screens

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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


@Composable
fun StartScreen(
    driveViewModel: DriveViewModel,
    onButtonClick: () -> Unit = {},
    onSecondClick: () -> Unit = {},
    signInLauncher: ActivityResultLauncher<Intent>
) {
    val account by driveViewModel.currentAccount.collectAsState()
    val activity = LocalContext.current as Activity
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
                onClick = {
                    if (account != null) {
                        Log.d("GoogleSignIn", "User is signed in as: ${account!!.email}")
                    } else {
                        Log.d("GoogleSignIn", "No user is currently signed in")
                    }
                 },
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
            if (account != null) {
                OutlinedButton(
                    onClick = {
                        driveViewModel.signOut(activity)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Greenish
                    ),
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = "log out",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                }
            } else {
                // If user is not signed in, allow them to sign in
                OutlinedButton(
                    onClick = {
                        val signInIntent = driveViewModel.getSignInIntent(activity)
                        signInLauncher.launch(signInIntent)
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
