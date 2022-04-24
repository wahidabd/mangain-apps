package com.wahidabd.mangain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.data.models.Komik
import com.wahidabd.mangain.domain.usecase.KomikindoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class KomikViewModel @Inject constructor(private val useCase: KomikindoUseCase): ViewModel() {

    val komik = useCase.komik().distinctUntilChanged().cachedIn(viewModelScope)
    val daftar = useCase.daftar().distinctUntilChanged().cachedIn(viewModelScope)
    val manhwa = useCase.manhwa().distinctUntilChanged().cachedIn(viewModelScope)
    val manhua = useCase.manhua().distinctUntilChanged().cachedIn(viewModelScope)

//    private val _komik: MutableLiveData<PagingData<Komik>> = MutableLiveData()
//    val komik: MutableLiveData<PagingData<Komik>> = _komik
//
//    fun komik(){
//        useCase.komik()
//            .onEach { _komik.postValue(it) }
//            .launchIn(viewModelScope)
//    }

}