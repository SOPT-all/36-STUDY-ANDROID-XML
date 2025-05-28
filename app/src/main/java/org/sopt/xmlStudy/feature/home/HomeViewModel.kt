package org.sopt.xmlStudy.feature.home

import org.sopt.xmlStudy.core.base.ui.BaseViewModel

class HomeViewModel : BaseViewModel<HomeState, HomeIntent, HomeSideEffect>(HomeState()) {
    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.MyProfileClick -> postSideEffect(HomeSideEffect.NavigateToMyPage)
        }
    }
}