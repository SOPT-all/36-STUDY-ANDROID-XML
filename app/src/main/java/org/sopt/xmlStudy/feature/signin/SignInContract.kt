package org.sopt.xmlStudy.feature.signin

import org.sopt.xmlStudy.core.base.ui.Intent
import org.sopt.xmlStudy.core.base.ui.SideEffect
import org.sopt.xmlStudy.core.base.ui.UiState

data class SignInState(
    val id: String = "",
    val password: String = ""
) : UiState

sealed interface SignInIntent : Intent {
    data class UpdateId(val id: String) : SignInIntent
    data class UpdatePw(val password: String) : SignInIntent
    object Login : SignInIntent
}

sealed interface SignInSideEffect : SideEffect {
    data object NavigateToLogin : SignInSideEffect
    data object FailureLogin : SignInSideEffect
}
