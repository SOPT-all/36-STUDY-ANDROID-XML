package org.sopt.xmlStudy.feature.signin

import org.sopt.xmlStudy.core.base.ui.BaseViewModel


class SignInViewModel : BaseViewModel<SignInState, SignInIntent, SignInSideEffect>(SignInState()) {

    override fun handleIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.UpdateId -> reduce { copy(id = intent.id) }
            is SignInIntent.UpdatePw -> reduce { copy(password = intent.password) }
            is SignInIntent.Login -> {
                if (currentState.id.isNotBlank() && currentState.password.isNotBlank()) {
                    postSideEffect(SignInSideEffect.NavigateToLogin)
                } else {
                    postSideEffect(SignInSideEffect.FailureLogin)
                }
            }
        }
    }
}