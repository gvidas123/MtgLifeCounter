package com.example.mtglifecounter
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mtglifecounter.screens.CommanderScreen
import com.example.mtglifecounter.screens.EndScreen
import com.example.mtglifecounter.screens.StartScreen
import com.example.mtglifecounter.screens.PlayerSelectionScreen
import com.example.mtglifecounter.ui.theme.CounterViewModel
import com.example.mtglifecounter.ui.theme.DriveViewModel


enum class Screens(@StringRes title: Int) {
    Start(title = R.string.app_name),
    Commander(title = R.string.commander),
    Selection(title = R.string.selection),
    EndGame(title = R.string.end_game)
}

@Composable
fun MtgLifeCounterApp(
    driveViewModel: DriveViewModel,
    counterViewModel: CounterViewModel,
    navController: NavHostController = rememberNavController(),
    signInLauncher: ActivityResultLauncher<Intent>
) {
    val activity = LocalContext.current as ComponentActivity
    Scaffold {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Start.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = Screens.Start.name) {
                StartScreen(
                    driveViewModel,
                    onButtonClick = { navController.navigate(Screens.Selection.name)},
                    onSecondClick = {},
                    signInLauncher = signInLauncher

                )
            }
            composable(route = Screens.Selection.name) {
                PlayerSelectionScreen(
                    viewModel = counterViewModel,
                    onButtonClick = { navController.navigate(Screens.Commander.name)}
                )
            }
            composable(route = Screens.Commander.name) {
                CommanderScreen(
                    viewModel = counterViewModel,
                    onButtonClick = { navController.navigate(Screens.EndGame.name) }
                )
            }
            composable(route = Screens.EndGame.name) {
                EndScreen(
                    viewModel = counterViewModel
                )

            }
        }
    }
}
