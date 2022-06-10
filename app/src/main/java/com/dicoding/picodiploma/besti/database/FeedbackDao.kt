package com.dicoding.picodiploma.besti.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FeedbackDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(feedback: Feedback)

    @Update
    fun update(feedback: Feedback)

    @Delete
    fun delete(feedback: Feedback)

    @Query("SELECT * from feedback ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Feedback>>
}