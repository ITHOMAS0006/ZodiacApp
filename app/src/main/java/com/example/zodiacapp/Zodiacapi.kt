package com.example.zodiacapp
import retrofit2.http.GET
import retrofit2.http.Path

interface Zodiacapi {
    @GET("horoscopes")
    suspend fun getDailyHoroscopes(): List<DailyHoroscopeResponse>
}