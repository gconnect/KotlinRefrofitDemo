package com.glory.kotlinretrofitdemo.model

import com.google.gson.annotations.SerializedName

data class PlantDTO (
    @SerializedName("id")
    var plantId : Int = 0,
    var genus : String,
    var species : String,
    var cultivar : String,
    var common : String
)