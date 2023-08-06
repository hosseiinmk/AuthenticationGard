package com.hosseinmohammadkarimi.authenticationgard.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    @ColumnInfo(name = "is_login")
    val isLogin: Boolean
)
