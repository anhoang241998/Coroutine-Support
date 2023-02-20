package com.example.newscoroutine.data.remote.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        fun getService(): CatsFactService {
            return Retrofit.Builder()
                .baseUrl("https://cat-fact.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatsFactService::class.java)
        }
    }
}