package com.hosseinmohammadkarimi.authenticationgard.domain.use_case

data class UserUseCases(
    val registerUser: RegisterUser,
    val getUser: GetUser,
    val getUsers: GetUsers
)