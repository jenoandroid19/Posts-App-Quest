package com.jeno.androidquest1.presentation.views.post.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeno.androidquest1.R
import com.jeno.androidquest1.presentation.ui.compose.CommonHeader
import com.jeno.androidquest1.presentation.ui.compose.PostOverview

@Composable
fun PostOverviewScreen(
    viewModel: PostOverviewViewModel,
    onBackClick: () -> Unit
){
    val state by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommonHeader(
            headerText = "Overview",
            leadingIcon = R.drawable.back_arrow,
            leadingIconClick = onBackClick
        )
        state.post?.let {
            PostOverview(
                post = it
            )
        } ?: Text(
            text = "not found"
        )
    }

}