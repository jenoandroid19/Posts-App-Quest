package com.jeno.androidquest1.presentation.views.post.overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeno.androidquest1.presentation.ui.models.PostOverviewModel
import com.jeno.androidquest1.data.remote.entity.PostEntity
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
class PostOverviewViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val postId: Int? = savedStateHandle["postId"]


    private val state = MutableStateFlow(PostOverviewState())
    internal val uiState: StateFlow<PostOverviewState> = state.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()


    init {
        viewModelScope.launch {
            postId?.let {
                postsRepository.getPostById(
                    id = it
                ).fold(
                    onSuccess = { post ->
                        state.update {
                            it.copy(
                                post = post.mapToPostOverviewModel()
                            )
                        }
                    },
                    onFailure = { throwable ->
                        triggerToast(throwable.message.toString())
                    }
                )
            }

        }
    }

    private fun PostEntity.mapToPostOverviewModel() : PostOverviewModel {
        return PostOverviewModel(
            id = id,
            userId = userId.toString(),
            postTitle = title,
            postDescription = body
        )
    }

    private fun triggerToast(message: String){
        viewModelScope.launch {
            _toastEvent.emit(message)
        }
    }

}