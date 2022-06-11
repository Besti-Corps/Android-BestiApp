package com.dicoding.picodiploma.besti.api

import com.dicoding.picodiploma.besti.dataclass.*
import okhttp3.MultipartBody
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

    @Multipart
    @POST("predict")
    fun predict(
        @Part file: MultipartBody.Part,
    ): Call<PredictionResponse>

    @GET("users/get_info")
    fun getInfo(
        @Header("Authorization") authToken: String,
    ): Call<InfoResponse>

    @FormUrlEncoded
    @PUT("users/update")
    fun getUpdate(
        @Header("Authorization") authToken: String,
        @Field("name") name: String,
        @Field("profession") profession: String,
        @Field("gender") gender: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<UpdateResponse>

    @GET("review")
    fun getReview(
        @Header("Authorization") authToken: String,
    ): Call<GetReviewResponse>

    @FormUrlEncoded
    @POST("review")
    fun postReview(
        @Header("Authorization") authToken: String,
        @Field("comment") comment: String,
    ): Call<PostReviewResponse>

}