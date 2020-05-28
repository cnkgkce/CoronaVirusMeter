package com.example.coronavirusmeter.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coronavirusmeter.model.DataModel

@Dao
interface DataDao {

    @Query("SELECT * FROM DATA_TABLE WHERE uuid = :uuid")
   suspend fun getOneData(uuid : Int) : DataModel

    @Insert
    suspend fun insertAll(vararg data : DataModel) : List<Long>

    @Query("SELECT * FROM DATA_TABLE")
   suspend fun getAllData()  : List<DataModel>


}