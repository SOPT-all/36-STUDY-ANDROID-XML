package org.sopt.xmlStudy.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UI_STATE : UiState, INTENT : Intent, SIDE_EFFECT : SideEffect>(
    initialState: UI_STATE
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _intentFlow = MutableSharedFlow<INTENT>()
    val intentFlow: SharedFlow<INTENT> = _intentFlow.asSharedFlow()

    private val _sideEffect: Channel<SIDE_EFFECT> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    protected val currentState: UI_STATE
        get() = _uiState.value

    init {
        viewModelScope.launch {
            _intentFlow.collect { intent ->
                handleIntent(intent)
            }
        }
    }

    fun sendIntent(intent: INTENT) {
        viewModelScope.launch {
            _intentFlow.emit(intent)
        }
    }

    protected fun reduce(reducer: UI_STATE.() -> UI_STATE) {
        _uiState.update { currentState.reducer() }
    }

    protected fun postSideEffect(vararg effects: SIDE_EFFECT) {
        effects.forEach { effect ->
            viewModelScope.launch { _sideEffect.send(effect) }
        }
    }

    abstract fun handleIntent(intent: INTENT)
}