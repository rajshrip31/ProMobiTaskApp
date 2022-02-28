package com.example.promobitaskapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.promobitaskapp.model.bookresponse.Book

@Dao
interface DaoBookAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(book: Book)

    @Query("Select * from Book order by rank ASC")
    fun getAllBooks(): List<Book>

    @Query("Select * from Book WHERE rank=:rankData")
    fun getBookByRank(rankData:Int?): Book

    @Query("SELECT COUNT(rank) FROM Book")
    fun getBooksCount(): Int
}