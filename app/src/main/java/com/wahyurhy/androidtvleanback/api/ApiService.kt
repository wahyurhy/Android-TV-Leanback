package com.wahyurhy.androidtvleanback.api

import com.wahyurhy.androidtvleanback.model.response.DetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int, @Query("api_key") apiKey: String
    ): Response<DetailResponse>

}