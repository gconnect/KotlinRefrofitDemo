package com.glory.kotlinretrofitdemo

import com.glory.kotlinretrofitdemo.model.PlantList
import retrofit2.Call
import retrofit2.http.GET

interface PlantService{

    @GET("perl/mobile/viewplantsjson.pl?Combined_Name=e")
    fun getAllPlant() : Call<PlantList>

    @GET("perl/mobile/viewplantsjson.pl?Combined_Name=Oak")
    fun getOakPlant() : Call<PlantList>

}