package com.example.appcontactsretrofit.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsApi {

    @GET("api/")
    suspend fun getAllContacts(@Query("results") results: Int): Item

}