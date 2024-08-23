package com.example.mtglifecounter
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mtglifecounter.screens.CommanderScreen
import com.example.mtglifecounter.screens.StartScreen
import com.example.mtglifecounter.screens.PlayerSelectionScreen
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState


enum class Screens(@StringRes title: Int) {
    Start(title = R.string.app_name),
    Commander(title = R.string.commander),
    Selection(title = R.string.selection)
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
                    onButtonClick = { navController.navigate(Screens.Selection.name) }
                )

            }
            composable(route = Screens.Commander.name) {
                CommanderScreen()
            }
            composable(route = Screens.Selection.name) {
                PlayerSelectionScreen()
            }
        }
    }


}




