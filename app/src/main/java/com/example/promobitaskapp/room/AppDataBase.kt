package com.example.promobitaskapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.promobitaskapp.model.bookresponse.Book

@Database(entities = [Book::class],
    version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDaoBookAccess() : DaoBookAccess

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDbClient(context: Context) : AppDataBase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDataBase::class.java, "AppDatabase")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }

}