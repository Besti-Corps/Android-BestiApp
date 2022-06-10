package com.dicoding.picodiploma.besti

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.besti.view.home.ui.profile.feedback.FeedbackViewModel
import com.dicoding.picodiploma.besti.view.home.ui.profile.feedback.ListFeedbackViewModel

class ViewModelFactorys private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactorys? = null
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactorys {
            if (INSTANCE == null) {
                synchronized(ViewModelFactorys::class.java) {
                    INSTANCE = ViewModelFactorys(application)
                }
            }
            return INSTANCE as ViewModelFactorys
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFeedbackViewModel::class.java)) {
            return ListFeedbackViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FeedbackViewModel::class.java)) {
            return FeedbackViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}