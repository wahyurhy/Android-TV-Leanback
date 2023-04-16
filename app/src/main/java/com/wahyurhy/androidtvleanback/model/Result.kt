package com.wahyurhy.androidtvleanback.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("details")
    val details: List<Detail>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)