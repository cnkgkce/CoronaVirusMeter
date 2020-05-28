package com.example.coronavirusmeter.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coronavirusmeter.db.DataDatabase
import com.example.coronavirusmeter.model.DataModel
import com.example.coronavirusmeter.service.DataService
import com.example.coronavirusmeter.ui.FeedActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : BaseViewModel(application) {

    var dataLiveData = MutableLiveData<DataModel>()

    fun getDataFromRoom(uuid : Int){
        launch {

            val dao = DataDatabase(getApplication()).getDao()
            var data = dao.getOneData(uuid)
            dataLiveData.value = data
        }
    }
}