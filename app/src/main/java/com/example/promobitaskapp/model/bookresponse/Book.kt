package com.example.promobitaskapp.model.bookresponse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
data class Book(
    @ColumnInfo(name = "age_group")
    val age_group: String,
    @ColumnInfo(name = "amazon_product_url")
    val amazon_product_url: String,
    @ColumnInfo(name = "article_chapter_link")
    val article_chapter_link: String,
    @ColumnInfo(name = "asterisk")
    val asterisk: Int,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "book_image")
    val book_image: String,
    @ColumnInfo(name = "book_image_height")
    val book_image_height: Int,
    @ColumnInfo(name = "book_image_width")
    val book_image_width: Int,
    @ColumnInfo(name = "book_review_link")
    val book_review_link: String,
    @ColumnInfo(name = "book_uri")
    val book_uri: String,
    @ColumnInfo(name = "contributor")
    val contributor: String,
    @ColumnInfo(name = "contributor_note")
    val contributor_note: String,
    @ColumnInfo(name = "dagger")
    val dagger: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "first_chapter_link")
    val first_chapter_link: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "primary_isbn10")
    val primary_isbn10: String,
    @ColumnInfo(name = "primary_isbn13")
    val primary_isbn13: String,
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @ColumnInfo(name = "rank")
    val rank: Int,
    @ColumnInfo(name = "rank_last_week")
    val rank_last_week: Int,
    @ColumnInfo(name = "sunday_review_link")
    val sunday_review_link: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "weeks_on_list")
    val weeks_on_list: Int
)

{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
