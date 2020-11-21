package com.example.arunatest.repository

import com.example.arunatest.OperationCallback
import com.example.arunatest.models.DataModel
import com.example.arunatest.models.DataSource

class DataRepository(private val dataSource: DataSource) {

    fun fetchData(callback: OperationCallback<DataModel>) {
        dataSource.retrieveData(callback)
    }

    fun cancel() {
        dataSource.cancel()
    }


}