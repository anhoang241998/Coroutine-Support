package com.example.newscoroutine.data.remote.config

import com.example.newscoroutine.data.remote.cats_fact_list.CatsFactListDto
import retrofit2.http.GET

interface CatsFactService {
    @GET("facts")
    suspend fun fetchCatsFact(): CatsFactListDto
}