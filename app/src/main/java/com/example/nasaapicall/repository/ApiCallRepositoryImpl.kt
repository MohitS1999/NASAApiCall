package com.example.nasaapicall.repository

import com.example.nasaapicall.api.NasaApi
import com.example.nasaapicall.model.APODModel
import retrofit2.Response

class ApiCallRepositoryImpl(private val nasaApi : NasaApi) : ApiCallRepository {

    override suspend fun getData() = nasaApi.getData()
}