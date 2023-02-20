package com.example.newscoroutine.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscoroutine.data.remote.cats_fact_list.CatsFactListDtoItem
import com.example.newscoroutine.home.presentation.CatsFactItemViewsState
import com.example.newscoroutine.home.presentation.CatsListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _catFacts: MutableLiveData<CatsListViewState> = MutableLiveData()
    val catFacts: LiveData<CatsListViewState> = _catFacts

    fun fetchCatFacts() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _catFacts.postValue(CatsListViewState.Loading)
            val catsFactList = repository.fetchCatFacts().toMutableList()
                .mapIndexed { index, catsFactListDtoItem ->
                    CatsFactItemViewsState(
                        id = "$index",
                        title = catsFactListDtoItem.type + " $index",
                        description = catsFactListDtoItem.text
                    )
                }
            _catFacts.postValue(CatsListViewState.Content(catsFactList))
        } catch (e: Exception) {
            _catFacts.postValue(CatsListViewState.Error)
            e.printStackTrace()
        }
    }

}