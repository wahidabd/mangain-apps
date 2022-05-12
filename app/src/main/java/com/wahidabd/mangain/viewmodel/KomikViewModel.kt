package com.wahidabd.mangain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.mangain.data.models.Komik
import com.wahidabd.mangain.domain.usecase.komik.KomikindoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class KomikViewModel @Inject constructor(private val useCase: KomikindoUseCase): ViewModel() {

    val komik = useCase.komik().distinctUntilChanged().cachedIn(viewModelScope)
    val daftar = useCase.daftar().distinctUntilChanged().cachedIn(viewModelScope)
    val manhwa = useCase.manhwa().distinctUntilChanged().cachedIn(viewModelScope)
    val manhua = useCase.manhua().distinctUntilChanged().cachedIn(viewModelScope)
    fun search(s: String): Flow<PagingData<Komik>> =
        useCase.search(s).distinctUntilChanged().cachedIn(viewModelScope)
}