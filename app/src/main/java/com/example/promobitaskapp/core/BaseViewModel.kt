package com.example.promobitaskapp.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData=  MutableLiveData<String> ()
    val successLiveData= MutableLiveData<Boolean>()

}