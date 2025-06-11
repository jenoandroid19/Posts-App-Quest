package com.jeno.androidquest1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeno.androidquest1.presentation.PostListing
import com.jeno.androidquest1.presentation.PostOverview
import com.jeno.androidquest1.presentation.ui.compose.RowItem
import com.jeno.androidquest1.presentation.ui.models.PostItemModel
import com.jeno.androidquest1.presentation.views.post.listing.PostListingScreen
import com.jeno.androidquest1.presentation.views.post.listing.PostListingViewModel
import com.jeno.androidquest1.presentation.views.post.overview.PostOverviewScreen
import com.jeno.androidquest1.presentation.views.post.overview.PostOverviewViewModel
import com.jeno.androidquest1.ui.theme.AndroidQuest1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,
                startDestination = PostListing
            ) {
                composable<PostListing>{
                    PostListingScreen(
                        viewModel = hiltViewModel<PostListingViewModel>()
                    ) {
                        navController.navigate(PostOverview(postId = it))
                    }
                }
                composable<PostOverview> {
                    PostOverviewScreen(viewModel = hiltViewModel<PostOverviewViewModel>()) {
                        navController.navigateUp()
                    }
                }
            }

        }
    }
}


