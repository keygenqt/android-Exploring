package com.keygenqt.exploring.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keygenqt.exploring.R

@Composable
fun ExploringScreen4() {
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
                text = stringResource(id = R.string.exploring_page_4),
                style = MaterialTheme.typography.h5,
            )
        }
        Box(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxWidth()
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.page_4),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}