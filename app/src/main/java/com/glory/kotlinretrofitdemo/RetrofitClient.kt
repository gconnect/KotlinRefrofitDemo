package com.glory.kotlinretrofitdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val BASE_URL = "https://www.plantplaces.com/"
    private  var retrofit: Retrofit? = null
    private val TAG = "RetrofitClient"

    /* Creating an instance of Retrofit Client*/

    val apiClient: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }


}
