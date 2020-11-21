package com.example.arunatest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arunatest.OperationCallback
import com.example.arunatest.models.DataModel
import com.example.arunatest.repository.DataRepository


class MainViewModel(private val repository: DataRepository) : ViewModel() {

    private val _datas = MutableLiveData<List<DataModel>>().apply { value = emptyList() }
    val datas: LiveData<List<DataModel>> = _datas

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    /*
    If you require that the data be loaded only once, you can consider calling the method
    "loadMuseums()" on constructor. Also, if you rotate the screen, the service will not be called.

    init {
        //loadMuseums()
    }
     */


    fun loadDatas() {
        repository.fetchData(object : OperationCallback<DataModel> {
            override fun onError(error: String?) {
                _isViewLoading.value = false
                _onMessageError.value = error
            }

            override fun onSuccess(data: List<DataModel>?) {
                _isViewLoading.value = false
                if (data.isNullOrEmpty()) {
                    _isEmptyList.value = true

                } else {
                    _datas.value = data
                }
            }
        })
    }

}