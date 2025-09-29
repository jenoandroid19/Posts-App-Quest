package com.jeno.androidquest1.presentation.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeno.androidquest1.R

@Composable
fun CommonHeader(
    modifier: Modifier = Modifier,
    headerText: String,
    @DrawableRes leadingIcon: Int? = null,
    leadingIconClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        leadingIcon?.let {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { leadingIconClick() },
                painter = painterResource(id = it),
                tint = Color.Black,
                contentDescription = null,
            )
        } ?: Spacer(modifier = Modifier.size(24.dp))
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(1f),
            text = headerText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowCommonHeaderPreview(){
    CommonHeader(
        headerText = "Post Listing",
        leadingIcon = R.drawable.back_arrow
    )
}

@Preview(showBackground = true)
@Composable
fun ShowCommonHeaderPreview2(){
    CommonHeader(
        headerText = "Post Listing",
    )
}