package com.dicoding.picodiploma.besti.view.home.ui.profile.review

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.PreferenceHelper.Companion.PREF_TOKEN
import com.dicoding.picodiploma.besti.api.Retrofit
import com.dicoding.picodiploma.besti.dataclass.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    val listReview = MutableLiveData<ArrayList<listReviewItem>>()

    fun getReview(): LiveData<ArrayList<listReviewItem>> {
        return listReview
    }

    fun ListReview(authToken: String) {
        _isLoading.value = true
        Retrofit.apiService
            .getReview(authToken)
            .enqueue(object : Callback<GetReviewResponse> {
                override fun onResponse(
                    call: Call<GetReviewResponse>,
                    response: Response<GetReviewResponse>,
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val user = response.body()
                        listReview.postValue(response.body()!!.data)
                        Log.e("token", user!!.data.toString())
                    } else {
                        listReview.postValue(response.body()!!.data)
                        val fail = response.body()
                        Log.e("message", fail?.status.toString())
                    }
                }

                override fun onFailure(call: Call<GetReviewResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("Failure", t.message.toString())
                }
            })
    }

    val postReview = MutableLiveData<PostReviewResponse>()

    fun postReview(): LiveData<PostReviewResponse> {
        return postReview
    }

    fun PostReview(authToken: String, comment: String) {
        _isLoading.value = true
        Retrofit.apiService
            .postReview(authToken, comment)
            .enqueue(object : Callback<PostReviewResponse> {
                override fun onResponse(
                    call: Call<PostReviewResponse>,
                    responsePost: Response<PostReviewResponse>,
                ) {
                    _isLoading.value = false
                    if (responsePost.isSuccessful) {
                        val user = responsePost.body()
                        postReview.postValue(responsePost.body())
                        _snackbarText.value = Event(responsePost.body()?.status.toString())
                        Log.e("token", user!!.data.toString())
                    } else {
                        postReview.postValue(responsePost.body())
                        val fail = responsePost.body()
                        Log.e("message", fail?.status.toString())
                    }
                }

                override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("Failure", t.message.toString())
                }
            })
    }
}