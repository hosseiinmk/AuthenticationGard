package com.hosseinmohammadkarimi.authenticationgard.domain.use_case

import com.hosseinmohammadkarimi.authenticationgard.data.repository.UserRepository

class GetUser(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(username: String) = userRepository.getUser(username = username)
}