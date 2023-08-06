package com.hosseinmohammadkarimi.authenticationgard.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hosseinmohammadkarimi.authenticationgard.data.room.dao.UserDao
import com.hosseinmohammadkarimi.authenticationgard.domain.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}