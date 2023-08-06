package com.hosseinmohammadkarimi.authenticationgard.domain.use_case

import com.hosseinmohammadkarimi.authenticationgard.data.repository.UserRepository
import com.hosseinmohammadkarimi.authenticationgard.domain.model.User

class RegisterUser(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.registerUser(user)
    }
}