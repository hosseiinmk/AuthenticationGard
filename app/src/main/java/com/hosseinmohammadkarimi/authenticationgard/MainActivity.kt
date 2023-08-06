package com.hosseinmohammadkarimi.authenticationgard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.hosseinmohammadkarimi.authenticationgard.ui.presentation.home.HomeScreen
import com.hosseinmohammadkarimi.authenticationgard.ui.presentation.login.LoginScreen
import com.hosseinmohammadkarimi.authenticationgard.ui.presentation.profile.ProfileScreen
import com.hosseinmohammadkarimi.authenticationgard.ui.theme.AuthenticationGardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthenticationGardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable(
                            route = "home",
                            arguments = listOf(
                                navArgument(name = "page") {
                                    type = NavType.StringType
                                    defaultValue = "home"
                                }
                            ),
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = "https://www.example.com/{page}"
                                    action = Intent.ACTION_VIEW
                                }
                            )
                        ) { entry ->
                           entry.arguments?.getString("page")?.let {
                               HomeScreen(
                                   navController = navController,
                                   page = it
                               )
                           }
                        }
                        composable(route = "login") {
                            LoginScreen(navController = navController)
                        }
                        composable(route = "profile") {
                            ProfileScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}