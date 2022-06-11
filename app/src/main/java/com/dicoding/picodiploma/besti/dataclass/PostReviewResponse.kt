package com.dicoding.picodiploma.besti.dataclass

import com.google.gson.annotations.SerializedName

data class PostReviewResponse (
    @SerializedName("status")
    var status: String,
    @SerializedName("data")
    val data: ArrayList<listReviewItems>
)

data class listReviewItems (
    @SerializedName("user_id")
    var user_id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("comment")
    val comment: String,
)
