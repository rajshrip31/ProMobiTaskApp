package com.example.promobitaskapp.repository

import com.example.promobitaskapp.model.bookresponse.BookAPIResponse
import io.reactivex.Observable

interface AppRepository {

    fun getBookListData(): Observable<BookAPIResponse>
}