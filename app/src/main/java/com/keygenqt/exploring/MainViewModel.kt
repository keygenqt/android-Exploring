/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.keygenqt.exploring

import androidx.lifecycle.viewModelScope
import com.keygenqt.exploring.base.BaseViewModel
import com.keygenqt.exploring.base.UiEffect
import com.keygenqt.exploring.base.UiEvent
import com.keygenqt.exploring.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainContract {

    sealed class Event : UiEvent {
        object Success : Event()
        data class Failure(val error: Throwable) : Event()
    }

    data class State(
        val isReady: ReadyState,
    ) : UiState

    sealed class ReadyState {
        object Idle : ReadyState()
        object Success : ReadyState()
    }

    sealed class Effect : UiEffect {
        object ShowToast : Effect()
    }

}

@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState() = MainContract.State(MainContract.ReadyState.Idle)

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.Success -> {
                setState { copy(isReady = MainContract.ReadyState.Success) }
            }
            is MainContract.Event.Failure -> {
                setEffect { MainContract.Effect.ShowToast }
            }
        }
    }

    init {
        viewModelScope.launch {
            try {
                delay(2000L)
                setEvent(MainContract.Event.Success)
            } catch (ex: Exception) {
                setEvent(MainContract.Event.Failure(ex))
            }
        }
    }
}
