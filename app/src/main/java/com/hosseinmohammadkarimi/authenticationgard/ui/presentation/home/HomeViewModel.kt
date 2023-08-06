package com.hosseinmohammadkarimi.authenticationgard.ui.presentation.home

import androidx.lifecycle.ViewModel
import com.hosseinmohammadkarimi.authenticationgard.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {}