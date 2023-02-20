package com.example.newscoroutine.home.presentation

sealed class CatsListViewState {
    object Loading : CatsListViewState()
    object Error : CatsListViewState()
    data class Content(val productList: List<CatsFactItemViewsState>) : CatsListViewState()
}