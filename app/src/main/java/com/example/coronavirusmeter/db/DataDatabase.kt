package com.example.coronavirusmeter.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coronavirusmeter.model.DataModel

@Database(entities = arrayOf(DataModel::class),version = 1)
abstract class DataDatabase : RoomDatabase() {

    abstract fun getDao() : DataDao // Dışarıdan buraya ulaşacağız

        companion object{
            private val lock = Any()
            private var instance : DataDatabase? = null

            operator fun invoke(context: Context) = instance ?: synchronized(lock){
                instance ?: makeDatabase(context).also {
                    instance = it
                }

            }
            private fun makeDatabase(context :Context) = Room.databaseBuilder(context.applicationContext,DataDatabase::class.java,"myDatabase").build()
        }

}