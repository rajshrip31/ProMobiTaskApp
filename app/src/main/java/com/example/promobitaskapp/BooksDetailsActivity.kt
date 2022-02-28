package com.example.promobitaskapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.promobitaskapp.core.BaseActivity
import com.example.promobitaskapp.databinding.ActivityBooksDetailsBinding
import com.example.promobitaskapp.model.bookresponse.Book
import com.example.promobitaskapp.room.AppDataBase
import kotlinx.android.synthetic.main.item_layout_book_rv.*
import kotlinx.android.synthetic.main.item_layout_book_rv.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BooksDetailsActivity : BaseActivity() {

    private lateinit var dataBinding: ActivityBooksDetailsBinding
    private lateinit var detailsViewModel: BookDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_details)
        dataBinding = ActivityBooksDetailsBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        registerObserver()
        setUI()

    }

    private fun registerObserver() {
        detailsViewModel =
            ViewModelProviders.of(this)[BookDetailsViewModel::class.java]

        detailsViewModel.bookInfoLiveData.observe(this){
            val book = it
            dataBinding.tvTitle.text = "Title: "+book.title
            dataBinding.tvDesc.text = "Description: "+book.description
            dataBinding.tvRank.text = "Rank: "+book.rank.toString()
            dataBinding.tvAuthor.text = "Author: "+book.author
            dataBinding.tvPrice.text = "Price: "+book.price
            dataBinding.tvPublisher.text = "Publisher: "+book.publisher

            Glide.with(iv_book_pic.context)
                .load(book.book_image)
                .into(iv_book_pic)
        }
    }

    private fun setUI(){
        (this as AppCompatActivity).supportActionBar!!.title = "Books Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        val rank = intent.getStringExtra("RANK")
        detailsViewModel.getBookDetails(rank.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}