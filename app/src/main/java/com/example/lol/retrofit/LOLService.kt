package com.example.lol.retrofit


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LOLService {
    @GET("champion-mastery/v4/champion-masteries/by-summoner/Abp5HJeZmmzLZ8B7VEYFSbE1EWTno8obxLP4CDL8d80dkw")
    fun  getInformation(@Query("api_key") api_key: String) : Call<List<LOLResponse.LOLResponseItem>>
}