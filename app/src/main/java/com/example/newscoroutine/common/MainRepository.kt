package com.example.newscoroutine.common

import com.example.newscoroutine.data.remote.cats_fact_list.CatsFactListDto
import com.example.newscoroutine.data.remote.config.ApiClient

class MainRepository {

    suspend fun fetchCatFacts(): CatsFactListDto {
        return ApiClient.getService().fetchCatsFact()
    }

}