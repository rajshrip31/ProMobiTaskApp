package com.example.promobitaskapp.api

import com.example.promobitaskapp.model.bookresponse.BookAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiService {

    @GET("svc/books/v3/lists/current/hardcover-fiction.json?api-key=GV46GIpiEIxS4Ce0Uc9YPr0oEl9GSmWK")
    fun getBookList(): Call<BookAPIResponse>
}