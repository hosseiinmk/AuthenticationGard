package com.hosseinmohammadkarimi.authenticationgard.ui.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hosseinmohammadkarimi.authenticationgard.domain.model.User
import com.hosseinmohammadkarimi.authenticationgard.domain.use_case.PasswordValidation
import com.hosseinmohammadkarimi.authenticationgard.domain.use_case.UserUseCases
import com.hosseinmohammadkarimi.authenticationgard.domain.use_case.UsernameValidation
import com.hosseinmohammadkarimi.authenticationgard.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val usernameValidation = UsernameValidation()
    private val passwordValidation = PasswordValidation()

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var usernameError by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateUsername(input: String) {
        username = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun login() {
        val usernameResult = usernameValidation.isValid(username)
        val passwordResult = passwordValidation.isValid(password)
        val hasError = !usernameResult || !passwordResult
        if (hasError) {
            usernameError = usernameValidation.errorMessage
            passwordError = passwordValidation.errorMessage
            return
        }

        viewModelScope.launch {
            val user = getUser()
            if (user != null) loginToAccount(user)
            else registerUser()

            usernameError = ""
            username = ""
            passwordError = ""
            password = ""
        }
    }

    private suspend fun getUser(): User? {
        return userUseCases.getUser.invoke(username = username)
    }

    private suspend fun registerUser() {
        userUseCases.registerUser(User(username = username, password = password, isLogin = true))
        sendUiEvent(UIEvent.NavigateTo("profile"))
    }

    private fun loginToAccount(user: User) {
        if (password == user.password) {
            sendUiEvent(UIEvent.NavigateTo("profile"))
        }
    }

    private fun sendUiEvent(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}