package com.jeno.androidquest1.presentation.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonLoader(
    modifier: Modifier = Modifier,
    loadingText: String = "",
    backgroundColor: Color = Color.White
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = Color.Green,
        )
        if (loadingText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = loadingText,
                style = TextStyle(
                    fontSize = 24.sp
                ),
                color = Color.Black,
            )
        }
    }
}