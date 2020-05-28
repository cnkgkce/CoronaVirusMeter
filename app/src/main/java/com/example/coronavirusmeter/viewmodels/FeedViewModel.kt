package com.example.coronavirusmeter.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
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

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val _dataList = MutableLiveData<List<DataModel>>()
    val dataList : LiveData<List<DataModel>>
            get() = _dataList

    var compositeDisposable = CompositeDisposable()
    var dataService = DataService()


    fun getDataFromAPI(){
     compositeDisposable.addAll(
         dataService.getData().
             subscribeOn(Schedulers.newThread()).
             observeOn(AndroidSchedulers.mainThread()).
             subscribeWith(object : DisposableSingleObserver<List<DataModel>>(){
                 override fun onSuccess(t: List<DataModel>) {
                  _dataList.value = t  // Şuanda veriler internetten bizim oluşturdugumuz dataliste eklenmiş olacak
                    storeInSQLite(t)
                 }

                 override fun onError(e: Throwable) {
                     Toast.makeText(FeedActivity().applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
                 }


             })
        )


    }


    private fun storeInSQLite(dataList : List<DataModel>){
        launch {
            val dao = DataDatabase(getApplication()).getDao()
            val listLong = dao.insertAll(*dataList.toTypedArray())
            var i = 0
            while(i<dataList.size){
                dataList[i].uuid = listLong[i].toInt()
                i++
            }

        }

    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear() // Telefonun hafızası için çok önemli
    }

}