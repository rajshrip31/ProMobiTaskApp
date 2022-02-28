package com.example.promobitaskapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.promobitaskapp.api.IApiService
import com.example.promobitaskapp.api.RetrofitApiClient
import com.example.promobitaskapp.core.BaseViewModel
import com.example.promobitaskapp.model.bookresponse.Book
import com.example.promobitaskapp.repository.ImplAppRepository
import com.example.promobitaskapp.room.AppDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : BaseViewModel(application) {

    private var mContext: Context = application
    private var iApiService: IApiService
    var mdAppDatabase: AppDataBase? = null
    var bookListLiveData = MutableLiveData<List<Book>>()
    private lateinit var list : List<Book>

    init {
        var apiClient = RetrofitApiClient.getAPIClient()
        iApiService = apiClient.create(IApiService::class.java)
        mdAppDatabase = AppDataBase.getDbClient(mContext)
    }

    fun getBookApiData() {
        loadingLiveData.value = true
        CoroutineScope(Dispatchers.IO).launch {
            list = mdAppDatabase?.getDaoBookAccess()?.getAllBooks()!!
            if (list.isNotEmpty()) {
                loadingLiveData.postValue(false)
                bookListLiveData.postValue(list)
            } else {
              fetchFromNetwork()
            }
        }

    }

    private fun fetchFromNetwork() {
        val disposable = CompositeDisposable()
        val observable = ImplAppRepository(iApiService,mContext).getBookListData()
        val dispose = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                loadingLiveData.value = false
                if (response?.status != null) {
                    Log.d("Response", "Response Book data success:" + response.status)
                    if (response.status.equals("OK", true)) {
                        bookListLiveData.value = response.results?.books
                    } else {
                        errorLiveData.value = mContext.resources.getString(R.string.server_error)
                    }
                } else {
                    errorLiveData.value = mContext.resources.getString(R.string.server_error)
                }
            }

        disposable.add(dispose)
    }


}