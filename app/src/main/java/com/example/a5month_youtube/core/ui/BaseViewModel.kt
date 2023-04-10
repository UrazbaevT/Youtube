package com.example.a5month_youtube.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>()
}