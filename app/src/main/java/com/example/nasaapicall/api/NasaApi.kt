package com.example.nasaapicall.api

import com.example.nasaapicall.model.APODModel
import retrofit2.Response
import retrofit2.http.GET

interface NasaApi {

    companion object{
        const val BASE_URL = "https://api.nasa.gov/planetary/apod/"
    }

    @GET("?api_key=5YeheusRc0QgbASz4PvCVIaj2D9LDElVH6mvgyr3")
    suspend fun getData() : Response<APODModel>

}