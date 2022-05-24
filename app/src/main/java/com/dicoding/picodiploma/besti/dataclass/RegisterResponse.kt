package com.dicoding.picodiploma.besti.dataclass

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: dataid
)

data class dataid(
    @SerializedName("id")
    val id: Int,
)