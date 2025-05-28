package org.sopt.xmlStudy.feature.home

import org.sopt.xmlStudy.core.base.ui.Intent
import org.sopt.xmlStudy.core.base.ui.SideEffect
import org.sopt.xmlStudy.core.base.ui.UiState

data class HomeState(
    val g: String = ""
): UiState

sealed interface HomeIntent: Intent {
    data object MyProfileClick: HomeIntent
}

sealed interface HomeSideEffect: SideEffect {
    data object NavigateToMyPage: HomeSideEffect
}