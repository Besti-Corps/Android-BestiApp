package com.dicoding.picodiploma.besti.dataclass

import com.google.gson.annotations.SerializedName

data class InfoResponse (
    @SerializedName("status")
    var status: String,
    @SerializedName("data")
    val data: datainfo
)

data class datainfo(
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
