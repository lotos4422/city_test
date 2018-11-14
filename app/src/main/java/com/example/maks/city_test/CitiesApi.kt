package com.example.maks.city_test

import com.example.maks.city_test.json_objects.Cities
import com.example.maks.city_test.json_objects.Companie
import com.example.maks.city_test.json_objects.Companies
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CitiesApi {

    @GET("cities")
    fun getCites(): Single<Cities>

    @GET("cities/{id_city}/categories/{id}/companies")
    fun getCompaniesByCategory(
        @Path("id_city") city: Int,
        @Path("id") category: Int): Single<Companies>

    @GET("companies/{id}")
    fun getCompanieDetails(@Path("id") id: Int): Observable<Companie>
}