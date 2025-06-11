package com.jeno.androidquest1.presentation.views.post.listing

import com.jeno.androidquest1.presentation.ui.models.LoaderDisplayInfo
import com.jeno.androidquest1.presentation.ui.models.PostItemModel

data class PostListingState(
    val posts: List<PostItemModel> = emptyList(),
    val showLoader: LoaderDisplayInfo = LoaderDisplayInfo()
)
