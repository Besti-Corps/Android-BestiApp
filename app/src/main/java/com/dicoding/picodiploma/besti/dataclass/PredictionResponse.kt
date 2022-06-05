package com.dicoding.picodiploma.besti.dataclass

import androidx.datastore.preferences.protobuf.Int32Value
import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("predict")
    val predict: ArrayList<dataPredict>
)

data class dataPredict(
    @SerializedName("label")
    val label: String,
    @SerializedName("accuracy")
    val accuracy: Int
)


