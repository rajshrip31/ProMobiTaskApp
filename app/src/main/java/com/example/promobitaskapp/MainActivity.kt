package com.example.promobitaskapp

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promobitaskapp.adapter.BooksRecyclerviewAdapter
import com.example.promobitaskapp.core.BaseActivity
import com.example.promobitaskapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var adapter: BooksRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        registerObserver()
        setUI()


    }

    private fun registerObserver() {
        mainActivityViewModel =  ViewModelProviders.of(this)[MainActivityViewModel::class.java]


        mainActivityViewModel.loadingLiveData.observe(this) {
            if (it) {
                dataBinding.llProgressBar.visibility = View.VISIBLE
            } else {
                dataBinding.llProgressBar.visibility = View.GONE
            }
        }

        mainActivityViewModel.errorLiveData.observe(this) {
            if (it != null) {
                printLog("ProMobi errorLiveData :$it")
                showLongMessage(it)
            }
        }

        mainActivityViewModel.bookListLiveData.observe(this) {
            printLog("Response DATA :$it")
            adapter = BooksRecyclerviewAdapter(this, it)
            dataBinding.recyclerView.adapter = adapter

        }

    }

    private fun setUI() {
        dataBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BooksRecyclerviewAdapter(this, arrayListOf())
        dataBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                dataBinding.recyclerView.context,
                (dataBinding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        mainActivityViewModel.getBookApiData()

    }
}