package com.dicoding.picodiploma.besti.view.home.ui.profile.editprofile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.besti.api.Retrofit
import com.dicoding.picodiploma.besti.dataclass.InfoResponse
import com.dicoding.picodiploma.besti.dataclass.UpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfilViewModel : ViewModel() {
    var infoUser = MutableLiveData<InfoResponse>()

    fun getInfoResponse(): MutableLiveData<InfoResponse> {
        return infoUser
    }

    var updateUser = MutableLiveData<UpdateResponse>()

    fun getUpdateResponse(): MutableLiveData<UpdateResponse> {
        return updateUser
    }

    fun setInfo(authToken: String) {
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
                    } else {
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

    fun setUpdate(
        authToken: String,
        name: String,
        profession: String,
        gender: String,
        phone: String,
        email: String,
        password: String
    ) {
        Retrofit.apiService
            .getUpdate(authToken, name, profession, gender, phone, email, password)
            .enqueue(object : Callback<UpdateResponse> {
                override fun onResponse(
                    call: Call<UpdateResponse>,
                    response: Response<UpdateResponse>,
                ) {
                    if (response.isSuccessful) {
                        updateUser.postValue(response.body())
                        val user = response.body()
                        Log.e("message", user!!.status)
                    } else {
                        updateUser.postValue(response.body())
                        val fail = response.body()
                        Log.e("message", fail?.status.toString())
                    }
                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                    Log.e("Failure", t.message.toString())
                }
            })
    }
}