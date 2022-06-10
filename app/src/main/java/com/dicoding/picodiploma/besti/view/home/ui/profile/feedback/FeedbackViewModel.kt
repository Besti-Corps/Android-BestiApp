package com.dicoding.picodiploma.besti.view.home.ui.profile.feedback

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.besti.Repository.FeedbackRepository
import com.dicoding.picodiploma.besti.database.Feedback

class FeedbackViewModel(application: Application) : ViewModel(){
    private val mNoteRepository: FeedbackRepository = FeedbackRepository(application)
    fun insert(feedback: Feedback) {
        mNoteRepository.insert(feedback)
    }
    fun update(feedback: Feedback) {
        mNoteRepository.update(feedback)
    }
    fun delete(feedback: Feedback) {
        mNoteRepository.delete(feedback)
    }
}