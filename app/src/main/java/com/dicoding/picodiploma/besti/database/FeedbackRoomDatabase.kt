package com.dicoding.picodiploma.besti.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Feedback::class],
    version = 1)
abstract class FeedbackRoomDatabase : RoomDatabase() {
    abstract fun feedbackDao(): FeedbackDao
    companion object {
        @Volatile
        private var INSTANCE: FeedbackRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FeedbackRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FeedbackRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FeedbackRoomDatabase::class.java, "feedback_database")
                        .build()
                }
            }
            return INSTANCE as FeedbackRoomDatabase
        }
    }
}