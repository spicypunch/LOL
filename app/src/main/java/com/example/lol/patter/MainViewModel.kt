package com.example.lol.patter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel {
    private val _items = MutableLiveData<>()
    val items: LiveData<> get() = _items

    private val _title = MutableLiveData()
    val title: LiveData<> get() = _title

    fun getData() {
    }
}