package com.dicoding.picodiploma.besti.view.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.besti.dataclass.RegisterResponse
import com.dicoding.picodiploma.besti.api.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    var signupUser = MutableLiveData<RegisterResponse>()

    fun getRegisterResponse(): MutableLiveData<RegisterResponse> {
        return signupUser
    }

    fun register(name: String, profession: String, gender: String, phone: String,  email: String, password: String) {
        Retrofit.apiService
            .registerRequest(name, profession, gender, phone, email, password)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>,
                ) {
                    if (response.isSuccessful) {
                        signupUser.postValue(response.body())
                        val user = response.body()
                        Log.e("message", user!!.status)
                    }
                    else {
                        signupUser.postValue(response.body())
                        val fail = response.body()
                        Log.e("message", fail?.status.toString())
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e("Failure", t.message.toString())
                }
            })
    }
}
