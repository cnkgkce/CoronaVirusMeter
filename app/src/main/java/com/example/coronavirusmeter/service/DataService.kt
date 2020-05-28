package com.example.coronavirusmeter.service

import com.example.coronavirusmeter.model.DataModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataService {
    // Api in oluşturulduğu yerdir

    // https://raw.githubusercontent.com/  ---> Base URL
    // cnkgkce/deneme/master/API.json  ---> Ext Url

    private val BASE_URL = "https://raw.githubusercontent.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DataAPI::class.java)


    fun getData() : Single<List<DataModel>>{  // Bu metod sayesinde artık verileri çekebilir haldeyiz
        return api.getData()
    }

}