package org.sopt.xmlStudy.feature.main

import org.sopt.xmlStudy.core.base.BaseViewModel

class MainViewModel : BaseViewModel<MainState, MainIntent, MainSideEffect>(MainState()) {

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.UpdateId -> reduce { copy(id = intent.id) }
            is MainIntent.UpdatePw -> reduce { copy(password = intent.password) }
            is MainIntent.Login -> {
                if (currentState.id.isNotBlank() && currentState.password.isNotBlank()) {
                    postSideEffect(MainSideEffect.NavigateToLogin)
                } else {
                    postSideEffect(MainSideEffect.FailureLogin)
                }
            }
        }
    }
}