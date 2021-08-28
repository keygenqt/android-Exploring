package com.keygenqt.exploring.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.keygenqt.exploring.R
import com.keygenqt.exploring.compose.common.MainScaffold

@Composable
fun DoneScreen() {
    MainScaffold(
        icon = null,
        label = stringResource(id = R.string.done_title),
        navigationIconOnClick = { },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 60.dp, end = 60.dp, top = 60.dp, bottom = 120.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.done_text),
                    style = MaterialTheme.typography.h5,
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
            ) {
                Lottie(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun Lottie(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.fireworks_icon),
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}