package com.example.homework.ui.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework.data.userPost.UserPost

val textPadding = 10.dp
val columnPadding = 15.dp
val roundedCornerPadding = 15.dp
val errorTextFontSize = 20.sp
val screenBackgroundColor = Color(0xFFA2C3C3)
val errorButtonColor = Color(0xFF005353)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val state by homeViewModel.state.collectAsState()
    val error by homeViewModel.error.collectAsState()
    val isRefreshing by homeViewModel.isRefreshing.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { homeViewModel.refresh() })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackgroundColor)
            .pullRefresh(pullRefreshState)
    ) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        LazyColumn {
            if (state.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center),
                    )
                }
            }
            if (error) {
                item { ErrorContent(homeViewModel) }
            } else {
                items(state) { userPost: UserPost ->
                    HomeScreenContent(userPost = userPost)
                }
            }
        }
    }
}


@Composable
fun HomeScreenContent(userPost: UserPost) {
    Column(
        modifier = Modifier
            .padding(columnPadding)
            .clip(
                shape = RoundedCornerShape(roundedCornerPadding)
            )
            .background(Color.White),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(textPadding),
            text = userPost.title,
            textAlign = TextAlign.Start,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(textPadding),
            text = " - ${userPost.name}",
            textAlign = TextAlign.End,
        )
    }
}

@Composable
fun ErrorContent(homeViewModel: HomeViewModel) {
    val contextForToast = LocalContext.current.applicationContext
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(textPadding),
            text = "Oops! Something went wrong",
            textAlign = TextAlign.Center,
            fontSize = errorTextFontSize,
            fontWeight = FontWeight.SemiBold,
        )
        Button(
            onClick = {
                homeViewModel.refresh()
                Toast.makeText(contextForToast, "Retried!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .padding(top = textPadding)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = errorButtonColor),
        ) {
            Text(text = "Retry")
        }
        Button(
            onClick = {
                homeViewModel.fetchUserPostData()
                Toast.makeText(contextForToast, "Canceled!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .padding(top = textPadding)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = errorButtonColor),
        ) {
            Text(text = "Cancel")
        }
    }
}

