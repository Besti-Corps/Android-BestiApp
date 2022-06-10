package com.dicoding.picodiploma.besti.view.home.ui.selectImage

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.api.Retrofit
import com.dicoding.picodiploma.besti.dataclass.DataPredict
import com.dicoding.picodiploma.besti.dataclass.PredictionResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectImageViewModel : ViewModel() {

    val predictImage = MutableLiveData<PredictionResponse>()

    fun getPredict(): MutableLiveData<PredictionResponse> {
        return predictImage
    }

    fun setImage(file: MultipartBody.Part) {
        Retrofit.apiService
            .predict(file)
            .enqueue(object : Callback<PredictionResponse> {
                override fun onResponse(
                    call: Call<PredictionResponse>,
                    response: Response<PredictionResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            predictImage.postValue(response.body()!!)

                            Log.e("message", responseBody.status)
                        }
                    } else {
                        predictImage.postValue(response.body()!!)
                        val responseBody = response.body()
                        responseBody?.status?.let { Log.e("message", it) }
                    }
                }

                override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                    Log.e("Failure", t.message.toString())
                }
            })
    }
}