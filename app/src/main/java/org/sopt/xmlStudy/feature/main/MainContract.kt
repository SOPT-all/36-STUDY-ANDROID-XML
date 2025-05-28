package org.sopt.xmlStudy.feature.main

import org.sopt.xmlStudy.core.base.Intent
import org.sopt.xmlStudy.core.base.SideEffect
import org.sopt.xmlStudy.core.base.UiState

data class MainState(
    val id: String = "",
    val password: String = ""
) : UiState

sealed interface MainIntent : Intent {
    data class UpdateId(val id: String) : MainIntent
    data class UpdatePw(val password: String) : MainIntent
    object Login : MainIntent
}

sealed interface MainSideEffect : SideEffect {
    data object NavigateToLogin : MainSideEffect
    data object FailureLogin : MainSideEffect
}
