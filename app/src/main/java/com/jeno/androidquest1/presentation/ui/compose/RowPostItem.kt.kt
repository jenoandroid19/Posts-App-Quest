package com.jeno.androidquest1.presentation.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeno.androidquest1.presentation.ui.models.PostItemModel
import com.jeno.androidquest1.R

@Composable
fun RowItem(
    modifier: Modifier = Modifier,
    post: PostItemModel,
    clickEvent: () -> Unit
) {
    Card(
        modifier = modifier.padding(horizontal = 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        onClick = clickEvent
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = post.postTitle,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(R.string.user_posted, post.userId))
                        append(" ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    ) {
                        append(post.postDesc)
                    }
                },
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowRowItemPreview(){
    RowItem(
        modifier = Modifier.padding(vertical = 8.dp),
        post = PostItemModel(
            id = 1,
            userId = 25,
            postTitle = "Zorojuro was the best",
            postDesc = "There was a swordsman called Zoro, " +
                    "when he visited the land of Wano, " +
                    "he was called Zorojuro because he acted like a samurai"
        )
    ) { }
}