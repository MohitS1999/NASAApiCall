package com.example.nasaapicall.repository

import com.example.nasaapicall.model.APODModel
import retrofit2.Response

interface ApiCallRepository {
    suspend fun getData() : Response<APODModel>
}