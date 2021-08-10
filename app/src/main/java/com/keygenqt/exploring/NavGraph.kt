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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.keygenqt.exploring.compose.screens.DoneScreen
import com.keygenqt.exploring.compose.screens.ExploringScreen

@ExperimentalPagerApi
@Composable
fun NavGraph(
    navController: NavHostController,
) {
    val actions = remember(navController) {
        Actions(navController)
    }

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Exploring.route) {
            composable(NavScreen.Exploring.route) {
                ExploringScreen { event ->
                    when (event) {
                        is ExploringEvents.ToDone -> actions.navigateToDone.invoke()
                    }
                }
            }
            composable(NavScreen.Done.route) {
                DoneScreen()
            }
        }
    }
}


