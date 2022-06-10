package com.dicoding.picodiploma.besti.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.besti.database.Feedback
import com.dicoding.picodiploma.besti.database.FeedbackDao
import com.dicoding.picodiploma.besti.database.FeedbackRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FeedbackRepository(application: Application) {
    private val mFeedbacksDao: FeedbackDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FeedbackRoomDatabase.getDatabase(application)
        mFeedbacksDao = db.feedbackDao()
    }
    fun getAllNotes(): LiveData<List<Feedback>> = mFeedbacksDao.getAllNotes()
    fun insert(feedback: Feedback) {
        executorService.execute { mFeedbacksDao.insert(feedback) }
    }
    fun delete(feedback: Feedback) {
        executorService.execute { mFeedbacksDao.delete(feedback) }
    }
    fun update(feedback: Feedback) {
        executorService.execute { mFeedbacksDao.update(feedback) }
    }
}