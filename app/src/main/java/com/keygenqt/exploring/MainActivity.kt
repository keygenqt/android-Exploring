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

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.exploring.ui.theme.ExploringTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExploringTheme {
                NavGraph(rememberNavController())
            }
        }

        val ob = ViewTreeObserver.OnPreDrawListener { false }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest {
                when (it.isReady) {
                    is MainContract.ReadyState.Idle -> {
                        window.decorView.findViewById<View>(android.R.id.content)?.viewTreeObserver
                            ?.addOnPreDrawListener(ob)
                    }
                    is MainContract.ReadyState.Success -> {
                        window.decorView.findViewById<View>(android.R.id.content)?.viewTreeObserver
                            ?.removeOnPreDrawListener(ob)
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.effect.collectLatest {
                when (it) {
                    is MainContract.Effect.ShowToast -> {
                        Toast.makeText(this@MainActivity, "Error init application", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
