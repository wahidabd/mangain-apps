package com.wahidabd.mangain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private var _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    fun updateQuery(q: String){
        _query.postValue(q)
    }

}