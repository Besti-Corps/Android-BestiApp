package com.dicoding.picodiploma.besti.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Feedback(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var name: String? = null,
    @ColumnInfo(name = "feedback")
    var feedback: String? = null,
    @ColumnInfo(name = "date")
    var date: String? = null
) : Parcelable