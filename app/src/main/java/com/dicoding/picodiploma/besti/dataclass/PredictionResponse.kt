package com.dicoding.picodiploma.besti.dataclass

import android.os.Parcelable
import androidx.datastore.preferences.protobuf.Int32Value
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PredictionResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("predict")
    val predict: ArrayList<DataPredict>
)

@Parcelize
data class DataPredict(
    @SerializedName("label")
    val label: String,
    @SerializedName("accuracy")
    val accuracy: Int
):Parcelable


