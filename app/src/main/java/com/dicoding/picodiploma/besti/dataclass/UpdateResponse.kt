package com.dicoding.picodiploma.besti.dataclass

import com.google.gson.annotations.SerializedName

data class UpdateResponse (
    @SerializedName("status")
    var status: String,
    @SerializedName("data")
    val data: dataupdate
)

data class dataupdate(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("profession")
    val profession: String
)
