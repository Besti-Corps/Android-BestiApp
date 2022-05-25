package com.dicoding.picodiploma.besti.api

import com.dicoding.picodiploma.besti.dataclass.LoginResponse
import com.dicoding.picodiploma.besti.dataclass.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface API {

    @FormUrlEncoded
    @POST("users/register")
    fun registerRequest(
        @Field("name") name: String,
        @Field("profession") profession: String,
        @Field("gender") gender: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("users/login")
    fun loginRequest(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

}