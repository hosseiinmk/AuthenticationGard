package com.hosseinmohammadkarimi.authenticationgard.domain.use_case

import com.hosseinmohammadkarimi.authenticationgard.data.repository.UserRepository

class GetUsers(
    private val userRepository: UserRepository
) {

    operator fun invoke() = userRepository.getUsers()
}