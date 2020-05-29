package com.example.coronavirusmeter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.coronavirusmeter.R
import com.example.coronavirusmeter.model.DataModel
import com.example.coronavirusmeter.viewmodels.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.recylcer_row.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel : DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        val intent = intent
        val uuid = intent.getIntExtra("uuid",0)

        viewModel.getDataFromRoom(uuid)
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.dataLiveData.observe(this, Observer {dataModel->
           try {
               detailsCountryText.text = dataModel.country
               detailTotalConfirmedText.text = dataModel.totalConfirmed.toString()
               detailTotalDeathsText.text = dataModel.totalDeaths.toString()
               detailTotalRecoveredText.text = dataModel.totalRecovered.toString()
           }catch (e:Exception){
               e.printStackTrace()
           }
        })
    }
}
