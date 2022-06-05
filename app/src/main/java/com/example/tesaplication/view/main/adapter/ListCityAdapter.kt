package com.example.tesaplication.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tesaplication.databinding.ItemListCityBinding
import com.example.tesaplication.databinding.ItemListUserBinding
import com.example.tesaplication.util.ItemClickListener
import com.example.tesaplication.view.main.model.CityEntityPresentation
import com.example.tesaplication.view.main.model.UserEntityPresentation

class ListCityAdapter:RecyclerView.Adapter<ListCityAdapter.ViewHolder>() {

    val listData:ArrayList<CityEntityPresentation> = ArrayList()

    var listener: ItemClickListener<CityEntityPresentation>? = null

    fun setItemClickListener(itemClickListener: ItemClickListener<CityEntityPresentation>){
        listener = itemClickListener
    }


    fun setData(list:List<CityEntityPresentation>){
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCityAdapter.ViewHolder {
        return ViewHolder(ItemListCityBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ListCityAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(private val binding:ItemListCityBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: CityEntityPresentation){
            binding.run {
                txtCity.text = data.name

                txtCity.setOnClickListener {
                    listener?.onClick(data)
                }
            }
        }
    }


}