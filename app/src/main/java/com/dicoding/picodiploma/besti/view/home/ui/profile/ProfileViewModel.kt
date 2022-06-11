package com.dicoding.picodiploma.besti.view.home.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.besti.api.Retrofit
import com.dicoding.picodiploma.besti.dataclass.InfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    var infoUser = MutableLiveData<InfoResponse>()

    fun getInfoResponse(): MutableLiveData<InfoResponse> {
        return infoUser
    }

    fun setInfo(authToken : String) {
        Retrofit.apiService
            .getInfo(authToken)
            .enqueue(object : Callback<InfoResponse> {
                override fun onResponse(
                    call: Call<InfoResponse>,
                    response: Response<InfoResponse>,
                ) {
                    if (response.isSuccessful) {
                        infoUser.postValue(response.body())
                        val user = response.body()
                        Log.e("message", user!!.status)
                    }
                    else {
                        infoUser.postValue(response.body())
                        val fail = response.body()
                        Log.e("message", fail?.status.toString())
                    }
                }

                override fun onFailure(call: Call<InfoResponse>, t: Throwable) {
                    Log.e("Failure", t.message.toString())
                }
            })
    }
}