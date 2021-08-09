package com.keygenqt.exploring.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keygenqt.exploring.MainViewModel
import com.keygenqt.exploring.R
import com.keygenqt.kchat.modules.common.ui.compose.MainScaffold

@Composable
fun DoneScreen(viewModel: MainViewModel) {
    MainScaffold(
        icon = null,
        label = stringResource(id = R.string.done_title),
        navigationIconOnClick = { },
    ) { innerPadding ->

        val modifier = Modifier.padding(innerPadding)
        val padding = 16.dp

        Column(
            modifier = modifier
                .padding(start = padding, end = padding)
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {

        }
    }
}