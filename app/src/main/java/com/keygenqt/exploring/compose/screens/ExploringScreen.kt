package com.keygenqt.exploring.compose.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.keygenqt.exploring.MainViewModel
import com.keygenqt.exploring.R
import com.keygenqt.kchat.modules.common.ui.compose.MainScaffold
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun ExploringScreen(viewModel: MainViewModel) {
    MainScaffold(
        icon = null,
        label = stringResource(id = R.string.exploring_title),
        navigationIconOnClick = { },
    ) {

        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = 4)

        Box {
            HorizontalPager(
                dragEnabled = false,
                state = pagerState,
            ) { page ->
                when (page) {
                    0 -> ExploringScreen1()
                    1 -> ExploringScreen2()
                    2 -> ExploringScreen3()
                    3 -> ExploringScreen4()
                }
            }
            ConstraintLayout(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(30.dp)
                    .fillMaxWidth()
            ) {
                val (indicator, next) = createRefs()

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .padding(16.dp)
                        .constrainAs(indicator) {
                            top.linkTo(parent.top)
                            bottom.linkTo(next.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Button(
                    onClick = {
                        scope.launch {
                            val index = pagerState.currentPage + 1
                            if (index == pagerState.pageCount) {
                                Log.e("TAG", "DONE")
                            } else {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    },
                    modifier = Modifier
                        .constrainAs(next) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text(
                        text = stringResource(id = R.string.exploring_next).uppercase(),
                    )
                }
            }
        }
    }
}