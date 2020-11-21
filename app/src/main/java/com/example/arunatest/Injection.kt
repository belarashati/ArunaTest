package com.example.arunatest

import androidx.lifecycle.ViewModelProvider
import com.example.arunatest.models.DataSource
import com.example.arunatest.repository.DataRepository
import com.example.arunatest.viewmodel.ViewModelFactory

object Injection {
    private val dataSource: DataSource = RemoteDataSource(ApiClient)
    private val repository = DataRepository(dataSource)
    private val viewModelFactory = ViewModelFactory(repository)

    fun providerRepository(): DataSource {
        return dataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }
}