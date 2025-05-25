package org.sopt.xmlStudy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<MainState>(MainState())
    val uiState: StateFlow<MainState> = _uiState

    fun updateId(password: String) {
        _uiState.value = uiState.value.copy(id = password)
    }

    fun updatePassword(password: String) {
        _uiState.value = uiState.value.copy(password = password)
    }

    fun navigateToHome(): Boolean {
        return uiState.value.id.isNotBlank() && uiState.value.password.isNotBlank()
    }
}