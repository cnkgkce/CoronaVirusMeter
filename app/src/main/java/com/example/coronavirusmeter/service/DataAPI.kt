package com.example.coronavirusmeter.service

import com.example.coronavirusmeter.model.DataModel
import io.reactivex.Single
import retrofit2.http.GET


interface DataAPI {
    //Datayı @GET VEYA @POST metotlarıyla neler yapacağımızı yazacağımız yer burası

    // https://raw.githubusercontent.com/  ---> Base URL
    // cnkgkce/deneme/master/API.json  ---> Ext Url

    @GET("cnkgkce/deneme/master/API.json")
        fun getData() : Single<List<DataModel>>

}