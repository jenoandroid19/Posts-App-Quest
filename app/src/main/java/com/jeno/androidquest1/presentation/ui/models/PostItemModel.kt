package com.jeno.androidquest1.presentation.ui.models

data class PostItemModel(
    val id: Int,
    val userId: Int = 0,
    val postTitle: String = "",
    val postDesc: String = ""
)
