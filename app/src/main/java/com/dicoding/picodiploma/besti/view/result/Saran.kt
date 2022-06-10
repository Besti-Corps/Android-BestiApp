package com.dicoding.picodiploma.besti.view.result

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Saran(
    var name: String,
    var description: String,
    var photo: Int
) : Parcelable
