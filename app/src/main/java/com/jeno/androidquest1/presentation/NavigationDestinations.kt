package com.jeno.androidquest1.presentation

import kotlinx.serialization.Serializable

@Serializable
object PostListing

@Serializable
data class PostOverview(
    val postId: Int
)