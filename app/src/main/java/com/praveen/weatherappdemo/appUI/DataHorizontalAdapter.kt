package com.praveen.weatherappdemo.appUI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.praveen.weatherappdemo.R
import com.praveen.weatherappdemo.appUtil.ConstantFun
import com.squareup.picasso.Picasso

class DataHorizontalAdapter(
    private val dataList: List<DataModelClass.Weather>,
    ) : RecyclerView.Adapter<DataHorizontalAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hori_weather, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(dataList: DataModelClass.Weather) {
            val ivWeather = itemView.findViewById(R.id.ivWeather) as ImageView
            val tvName = itemView.findViewById(R.id.tvName) as TextView
            val tvDesc = itemView.findViewById(R.id.tvDesc) as TextView

            tvName.text = dataList.main
            tvDesc.text = dataList.description

            Picasso.get()
                .load(ConstantFun.getWeatherLink(dataList.main))
                .into(ivWeather)
        }
    }
}