package com.wahyurhy.androidtvleanback.model


import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("result")
    val result: List<Result>
)