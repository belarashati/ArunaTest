package com.example.arunatest

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arunatest.adapter.DataAdapter
import com.example.arunatest.models.DataModel
import com.example.arunatest.response.TitleOnly
import com.example.arunatest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var DataAdapter: DataAdapter
    private lateinit var viewModel: MainViewModel
    var listTitle: ArrayList<String> = ArrayList()
    var allData: List<DataModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        setupViewModel()
        initSearchView()
        setupUI()
    }

    private fun initSearchView() {
        recycler_view.layoutManager = LinearLayoutManager(recycler_view.context)
        recycler_view.setHasFixedSize(true)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                DataAdapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun setupUI() {
        DataAdapter = DataAdapter(listTitle)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = DataAdapter
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, Injection.provideViewModelFactory()
        ).get(MainViewModel::class.java)

        viewModel.datas.observe(this, renderDatas)

    }


    //observers
    private val renderDatas = Observer<List<DataModel>> {
        Log.v(TAG, "data updated $it")
        allData = it

        for (i in it){
            listTitle.add(i.title)
        }
        DataAdapter.submitList(listTitle, it)
    }

    //If you require updated data, you can call the method "loadMuseum" here
    override fun onResume() {
        super.onResume()
        viewModel.loadDatas()
    }

    companion object {
        const val TAG = "CONSOLE"
    }
}