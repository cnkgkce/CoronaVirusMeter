package com.example.coronavirusmeter.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.coronavirusmeter.R
import com.example.coronavirusmeter.listener.DataOnClickListener
import com.example.coronavirusmeter.model.DataModel
import com.example.coronavirusmeter.ui.DetailsActivity
import com.example.coronavirusmeter.ui.FeedActivity
import kotlinx.android.synthetic.main.recylcer_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class DataAdapter(var dataList : ArrayList<DataModel>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>(),DataOnClickListener {


    class DataViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recylcer_row,parent,false) // attach to roota dikkat
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.itemView.countryNameText.text =dataList[position].country
        holder.itemView.uuidText.text = dataList[position].uuid.toString() // Her bir viewın bir uuid sini şuan aldık bunu diğer tarafa
        //geçireceğiz intentle

        holder.itemView.setOnClickListener { onDataClicked(holder.itemView) }
    }

    fun updateList(newList : List<DataModel>){
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged() // Bildirim yapacağız
    }

    override fun onDataClicked(v: View) {
        var uuid = v.uuidText.text.toString().toInt()
        var intent = Intent(v.context,DetailsActivity::class.java)
        intent.putExtra("uuid",uuid)
        v.context.startActivity(intent)

    }

}

