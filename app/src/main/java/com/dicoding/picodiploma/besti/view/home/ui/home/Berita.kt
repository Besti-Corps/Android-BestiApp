package com.dicoding.picodiploma.besti.view.home.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Berita(
    var name:String,
    var description: String,
    var photo: Int
): Parcelable