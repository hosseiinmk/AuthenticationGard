package com.hosseinmohammadkarimi.authenticationgard.util

sealed class UIEvent {

    data class SnackbarShow(
        val message: String,
        val actionLabel: String? = null
    ): UIEvent()

    data class NavigateTo(val route: String): UIEvent()
}