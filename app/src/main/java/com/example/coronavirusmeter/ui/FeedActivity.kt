package com.example.coronavirusmeter.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronavirusmeter.R
import com.example.coronavirusmeter.adapter.DataAdapter
import com.example.coronavirusmeter.viewmodels.FeedViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {


    private lateinit var viewModel: FeedViewModel
    var adapter = DataAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        observeLiveData()

        val layoutManager = LinearLayoutManager(this)
        dataRecyclerList.layoutManager = layoutManager
        dataRecyclerList.adapter = adapter

    }


    fun observeLiveData() {
        viewModel.getDataFromAPI()
        viewModel.dataList.observe(this, Observer {
            adapter.updateList(it)
        })

    }

       override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logOut){
            var auth = FirebaseAuth.getInstance()
            Toast.makeText(applicationContext,"Good Bye !",Toast.LENGTH_LONG).show()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            auth.signOut()
            finish()
        }

        return super.onOptionsItemSelected(item)
    }


}




