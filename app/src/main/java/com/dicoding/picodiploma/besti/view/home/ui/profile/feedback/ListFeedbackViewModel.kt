package com.dicoding.picodiploma.besti.view.home.ui.profile.feedback

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.besti.Repository.FeedbackRepository
import com.dicoding.picodiploma.besti.database.Feedback

class ListFeedbackViewModel (application: Application) : ViewModel(){
    private val mNoteRepository: FeedbackRepository = FeedbackRepository(application)
    fun getAllNotes(): LiveData<List<Feedback>> = mNoteRepository.getAllNotes()
}