package com.hosseinmohammadkarimi.authenticationgard.di

import android.app.Application
import androidx.room.Room
import com.hosseinmohammadkarimi.authenticationgard.data.repository.UserRepository
import com.hosseinmohammadkarimi.authenticationgard.data.room.database.UserDatabase
import com.hosseinmohammadkarimi.authenticationgard.domain.use_case.GetUser
import com.hosseinmohammadkarimi.authenticationgard.domain.use_case.GetUsers
import com.hosseinmohammadkarimi.authenticationgard.domain.use_case.RegisterUser
import com.hosseinmohammadkarimi.authenticationgard.domain.use_case.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application) = Room.databaseBuilder(
        context = application,
        klass = UserDatabase::class.java,
        name = "users"
    ).build()

    @Provides
    @Singleton
    fun provideUserRepository(
        userDatabase: UserDatabase
    ) = UserRepository(userDao = userDatabase.userDao())

    @Provides
    @Singleton
    fun provideUserUseCases(userRepository: UserRepository) = UserUseCases(
        registerUser = RegisterUser(userRepository = userRepository),
        getUser = GetUser(userRepository = userRepository),
        getUsers = GetUsers(userRepository = userRepository)
    )
}