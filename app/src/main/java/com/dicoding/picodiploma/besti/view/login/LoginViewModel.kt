package com.dicoding.picodiploma.besti.view.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.besti.api.Retrofit
import com.dicoding.picodiploma.besti.dataclass.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    var loginUser = MutableLiveData<LoginResponse>()

    fun getLoginResponse(): MutableLiveData<LoginResponse> {
        return loginUser
    }

    fun login(email: String, password: String) {
        Retrofit.apiService
            .loginRequest(email, password)
            .enqueue(object : Callback<LoginResponse> {

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>,
                ) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        loginUser.postValue(response.body())
                        Log.e("token", user!!.data.token)
                    } else {
                        loginUser.postValue(response.body())
                        val fail = response.body()
                        Log.e("message", fail?.status.toString())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("Failure", t.message.toString())
                }
            })
    }
}
