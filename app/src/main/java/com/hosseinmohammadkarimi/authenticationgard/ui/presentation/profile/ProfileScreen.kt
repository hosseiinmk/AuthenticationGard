package com.hosseinmohammadkarimi.authenticationgard.ui.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hosseinmohammadkarimi.authenticationgard.util.UIEvent

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        profileViewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.NavigateTo -> {
                    navController.navigate(route = event.route) {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Screen")
    }
}