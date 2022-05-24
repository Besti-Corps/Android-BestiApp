package com.dicoding.picodiploma.besti.dataclass

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: datalogin
)

data class datalogin(
    @SerializedName("token")
    val token: String,
)