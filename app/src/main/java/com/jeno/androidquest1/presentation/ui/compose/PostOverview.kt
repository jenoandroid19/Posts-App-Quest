package com.jeno.androidquest1.presentation.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeno.androidquest1.presentation.ui.models.PostOverviewModel

@Composable
fun PostOverview(
    modifier: Modifier = Modifier,
    post: PostOverviewModel,
){
    Card(
        modifier = modifier.padding(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.LightGray, CircleShape)
                    .width(50.dp)
                    .height(50.dp)
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = post.userId,
                    color = Color.Yellow,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
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
                        append(post.userId)
                        append(" said")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    ) {
                        append(" ")
                        append(post.postDescription)
                    }
                },
                style = TextStyle(
                    textAlign = TextAlign.Center
                )

            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ShowPostOverviewPreview(){
    PostOverview(
        post = PostOverviewModel(
            id = 1,
            userId = "77",
            postTitle = "Zorojuro was the best",
            postDescription = "There was a swordsman called Zoro, " +
                    "when he visited the land of Wano, " +
                    "he was called Zorojuro because he acted like a samurai"
        )
    )
}