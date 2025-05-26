package org.sopt.a36_study_android_xml

data class SignInState(
    val id: String = "",
    val password: String = "",

) {
    val isButtonEnabled: Boolean = id.isNotEmpty() && password.isNotEmpty()
}
