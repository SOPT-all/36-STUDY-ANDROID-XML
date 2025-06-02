package org.sopt.a36_study_android_xml

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewmodel: ViewModel() {
    private val _signInState = MutableStateFlow(SignInState())
    val signInState: StateFlow<SignInState> = _signInState.asStateFlow()

    fun updateId(id: String) {
        _signInState.value = _signInState.value.copy(id = id)
    }

    fun updatePassword(password: String) {
        _signInState.value = _signInState.value.copy(password = password)
    }

    fun navigateToMain(): Boolean {
        return idPattern.matches(signInState.value.id) && passwordPattern.matches(signInState.value.password)
    }

    companion object {
        val idPattern = "^[a-z][a-z0-9]{5,11}$".toRegex()
        val passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#\$%^&*])[A-Za-z\\d~!@#\$%^&*]{8,15}$".toRegex()
    }
}