package com.jeno.androidquest1.presentation.views.post.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeno.androidquest1.data.remote.entity.PostEntity
import com.jeno.androidquest1.presentation.ui.models.LoaderDisplayInfo
import com.jeno.androidquest1.presentation.ui.models.PostItemModel
import com.jeno.androidquest1.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListingViewModel @Inject constructor(
    private val postsRepository: PostsRepository
): ViewModel() {
    private val state = MutableStateFlow(PostListingState())
    internal val uiState: StateFlow<PostListingState> = state.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    init {
        state.update {
            it.copy(
                showLoader = LoaderDisplayInfo(
                    showLoader = true,
                    message = "fetching"
                )
            )
        }
        viewModelScope.launch {
            postsRepository.fetchPostsAndStore().fold(
                onSuccess = { getPosts() },
                onFailure = { throwable ->
                    triggerToast(throwable.message.toString())
                }
            )
        }.invokeOnCompletion {
            state.update {
                it.copy(
                    showLoader = LoaderDisplayInfo()
                )
            }
        }
    }

    private fun triggerToast(message: String){
        viewModelScope.launch {
            _toastEvent.emit(message)
        }
    }

    private fun getPosts(){
        viewModelScope.launch {
            postsRepository.getAllPosts().collect { posts->
                state.update {
                    it.copy(
                        posts = posts.map { postItem -> postItem.mapToPostItem() }
                    )
                }
            }
        }
    }

    private fun PostEntity.mapToPostItem(): PostItemModel {
        return PostItemModel(
            id = id,
            userId = userId,
            postTitle = title,
            postDesc = body
        )
    }
}