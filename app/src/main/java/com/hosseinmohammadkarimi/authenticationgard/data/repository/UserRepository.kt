package com.hosseinmohammadkarimi.authenticationgard.data.repository

import com.hosseinmohammadkarimi.authenticationgard.data.room.dao.UserDao
import com.hosseinmohammadkarimi.authenticationgard.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getUsers() = userDao.getUsers()

    suspend fun getUser(username: String) = userDao.getUser(username = username)

    suspend fun registerUser(user: User) {
        userDao.registerUser(user = user)
    }
}