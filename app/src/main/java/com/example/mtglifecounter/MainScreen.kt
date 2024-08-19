package com.example.mtglifecounter

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mtglifecounter.screens.CommanderScreen
import com.example.mtglifecounter.screens.StartScreen
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState


enum class Screens(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Commander(title = R.string.commander)
}



@Composable
fun MtgLifeCounterApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        backStackEntry?.destination?.route ?: Screens.Start.name
    )
    Scaffold(

    ){
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Start.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = Screens.Start.name) {
                StartScreen(
                    onButtonClick = { navController.navigate(Screens.Commander.name) }
                )

            }
            composable(route = Screens.Commander.name) {
                CommanderScreen()
            }
        }
    }


}




