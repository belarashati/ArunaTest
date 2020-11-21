package com.example.arunatest.models

import com.example.arunatest.OperationCallback

interface DataSource {
    fun retrieveData(callback: OperationCallback<DataModel>)
    fun cancel()
}