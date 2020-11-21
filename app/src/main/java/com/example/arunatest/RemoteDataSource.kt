package com.example.arunatest

import android.util.Log
import android.widget.Toast
import com.example.arunatest.models.DataModel
import com.example.arunatest.models.DataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource(apiClient: ApiClient) : DataSource {

    private var call: Call<List<DataModel>>? = null
    private val service = apiClient.build()
    override fun retrieveData(callback: OperationCallback<DataModel>) {
        call = service?.datas()

        call?.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful)
                        callback.onSuccess(it)

                }
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }

}