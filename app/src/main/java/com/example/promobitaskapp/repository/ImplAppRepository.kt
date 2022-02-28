package com.example.promobitaskapp.repository

import android.content.Context
import com.example.promobitaskapp.api.IApiService
import com.example.promobitaskapp.model.bookresponse.BookAPIResponse
import com.example.promobitaskapp.room.AppDataBase
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImplAppRepository(private val iApiService: IApiService, private val mContext: Context) :
    AppRepository {

    override fun getBookListData(): Observable<BookAPIResponse> {
        return Observable.create {
            val apiCall = iApiService.getBookList()

            apiCall.enqueue(object : Callback<BookAPIResponse> {
                override fun onFailure(call: Call<BookAPIResponse>, t: Throwable) {
                    val response = BookAPIResponse("", "", 0, null, "false")
                    it.onNext(response)
                    it.onComplete()
                }

                override fun onResponse(call: Call<BookAPIResponse>,response: Response<BookAPIResponse>) {
                    when {
                        response.isSuccessful -> {
                            val responseData = response.body()
                            if (responseData != null && responseData.status.equals("OK", true)) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    for (item in responseData.results!!.books) {
                                        val mdAppDatabase = AppDataBase.getDbClient(mContext)
                                        mdAppDatabase.getDaoBookAccess().insertBooks(item)
                                    }
                                }
                                it.onNext(responseData)
                                it.onComplete()
                            }else{
                              responseError(it)
                            }
                        }
                        else -> {
                            responseError(it)
                        }
                    }
                }
            })
        }
    }

    private fun responseError(observableEmitter: ObservableEmitter<BookAPIResponse>) {
        val response = BookAPIResponse("", "", 0, null, "false")
        observableEmitter.onNext(response)
        observableEmitter.onComplete()
    }


}