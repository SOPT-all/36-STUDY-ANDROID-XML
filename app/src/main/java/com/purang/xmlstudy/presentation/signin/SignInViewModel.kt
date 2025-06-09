package com.purang.xmlstudy.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private val testEmail = "qqqq1234"
    private val testPassword = "qqqq1234"

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get()= _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    fun fetchEmail(email: String) = _email.postValue(email)
    fun fetchPassword(password: String) = _password.postValue(password)

    val isLoginEnabled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = validateInputs() }
        addSource(password) { value = validateInputs() }
    }

    private fun validateInputs(): Boolean {
        val emailValue = email.value.orEmpty()
        val passwordValue = password.value.orEmpty()
        return emailValue.isNotBlank() && passwordValue.isNotBlank()
    }

    fun validateSignIn(): Boolean {
        val emailValue = email.value.orEmpty()
        val passwordValue = password.value.orEmpty()
        return emailValue == testEmail && passwordValue == testPassword
    }
}
