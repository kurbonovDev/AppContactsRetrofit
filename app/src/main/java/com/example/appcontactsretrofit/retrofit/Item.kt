package com.example.appcontactsretrofit.retrofit

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("results")
    val results:List<ResultItem>
)

data class ResultItem(
    @SerializedName("gender")
    val gender:String,

    @SerializedName("name")
    val name:FullName,

    @SerializedName("email")
    val email:String,

    @SerializedName("phone")
    val phone:String,

    @SerializedName("picture")
    val picture:Pictures
)


data class FullName(
    @SerializedName("title")
    val title:String,
    @SerializedName("first")
    val first:String,
    @SerializedName("last")
    val last:String
)

data class Pictures(
    @SerializedName("large")
    val large:String,
    @SerializedName("medium")
    val medium:String,
    @SerializedName("thumbnail")
    val thumbnail:String
)