package com.example.coronavirusmeter.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "data_table")
data class DataModel(
    @SerializedName("Country")
    var country : String,
    @SerializedName("TotalConfirmed")
    var totalConfirmed : Int,
    @SerializedName("TotalDeaths")
    var totalDeaths : Int,
    @SerializedName("TotalRecovered")
    var totalRecovered : Int
)
{
@PrimaryKey(autoGenerate = true)
var uuid =0
}