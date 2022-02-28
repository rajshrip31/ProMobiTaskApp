package com.example.promobitaskapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.promobitaskapp.core.BaseViewModel
import com.example.promobitaskapp.model.bookresponse.Book
import com.example.promobitaskapp.room.AppDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel (application: Application) : BaseViewModel(application) {

    private var mContext: Context = application
    var mdAppDatabase: AppDataBase? = null
    var bookInfoLiveData = MutableLiveData<Book>()

    init {
        mdAppDatabase = AppDataBase.getDbClient(mContext)
    }

    fun getBookDetails(rank:String){
        CoroutineScope(Dispatchers.IO).launch {
            val book = mdAppDatabase?.getDaoBookAccess()?.getBookByRank(Integer.parseInt(rank))!!
            bookInfoLiveData.postValue(book)

        }
    }
}