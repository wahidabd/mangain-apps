package com.wahidabd.mangain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.mangain.core.Resource
import com.wahidabd.mangain.core.Status
import com.wahidabd.mangain.data.models.ChapterDetail
import com.wahidabd.mangain.data.models.MangaDetail
import com.wahidabd.mangain.data.responses.ChapterResponse
import com.wahidabd.mangain.data.responses.MangaDetailResponse
import com.wahidabd.mangain.domain.usecase.KomikindoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMangaViewModel @Inject constructor(private val useCase: KomikindoUseCase): ViewModel() {

    private val _resource: MutableLiveData<Resource<MangaDetailResponse>> = MutableLiveData()
    val resource: LiveData<Resource<MangaDetailResponse>> = _resource

    private val _result: MutableLiveData<MangaDetail> = MutableLiveData()
    val result: LiveData<MangaDetail> = _result

    private val _readerResource: MutableLiveData<Resource<ChapterResponse>> = MutableLiveData()
    val readerResource: LiveData<Resource<ChapterResponse>> = _readerResource

    private val _reader: MutableLiveData<ChapterDetail> = MutableLiveData()
    val reader: LiveData<ChapterDetail> = _reader

    fun detail(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.detail(id).collectLatest {
                    _resource.postValue(it)
                    if(it.status == Status.SUCCESS){
                        _result.postValue(it.data?.manga)
                    }
            }
        }
    }

    fun reader(id: String){
        viewModelScope.launch(Dispatchers.IO){
            useCase.chapter(id).collectLatest {
                _readerResource.postValue(it)
                if (it.status == Status.SUCCESS){
                    _reader.postValue(it.data?.chapter)
                }
            }
        }
    }
}