package com.example.promobitaskapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.promobitaskapp.BooksDetailsActivity
import com.example.promobitaskapp.R
import com.example.promobitaskapp.model.bookresponse.Book
import kotlinx.android.synthetic.main.item_layout_book_rv.view.*

class BooksRecyclerviewAdapter(private val context: Context, private val bookList: List<Book>)
    : RecyclerView.Adapter<BooksRecyclerviewAdapter.DataViewHolder>() {


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: Book,cnxt: Context) {
            Log.d("Bind Book data", "Book : $book")
            itemView.apply {
                tv_title.text = "Title: "+book.title
                tv_desc.text = "Description: "+book.description
                tv_rank.text = "Rank: "+book.rank.toString()
                tv_author.text = "Author: "+book.author
                tv_price.text = "Price: "+book.price
                tv_val_rank.text = book.rank.toString()

                Glide.with(iv_book_pic.context)
                    .load(book.book_image)
                    .into(iv_book_pic)
            }

            itemView.setOnClickListener {
                val intent = Intent(cnxt, BooksDetailsActivity::class.java)
                intent.putExtra("RANK",itemView.tv_val_rank.text.toString())
                cnxt.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_book_rv, parent, false))

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(bookList[position],context)
    }
}