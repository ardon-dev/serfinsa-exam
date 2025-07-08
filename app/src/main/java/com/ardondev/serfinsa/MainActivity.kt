package com.ardondev.serfinsa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ardondev.serfinsa.screens.CommerceDetailScreen
import com.ardondev.serfinsa.screens.HomeScreen
import com.ardondev.serfinsa.screens.JoinCommerceScreen
import com.ardondev.serfinsa.screens.LoginScreen
import com.ardondev.serfinsa.ui.theme.SerfinsaTheme

class MainActivity : ComponentActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SerfinsaTheme {
                NavHost(
                    navController = navController, startDestination = LoginRoute
                ) {
                    composable<LoginRoute> {
                        LoginScreen(
                            navController = navController
                        )
                    }
                    composable<HomeRoute> {
                        HomeScreen(navController = navController, viewModel = viewModel)
                    }
                    composable<JoinCommerceRoute> {
                        JoinCommerceScreen(navController = navController, viewModel = viewModel)
                    }
                    composable<CommerceDetailRoute> { backStackEntry ->
                        val route: CommerceDetailRoute = backStackEntry.toRoute()
                        CommerceDetailScreen(
                            id = route.id,
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}