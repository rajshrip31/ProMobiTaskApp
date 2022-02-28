package com.example.promobitaskapp.model.bookresponse

data class BookAPIResponse(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
    var results: Results?,
    val status: String
)