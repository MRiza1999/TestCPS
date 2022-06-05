package com.example.tesaplication.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tesaplication.databinding.ItemListUserBinding
import com.example.tesaplication.view.main.model.UserEntityPresentation

class ListUserAdapter:RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {

    val listData:ArrayList<UserEntityPresentation> = ArrayList()

    fun setData(list:List<UserEntityPresentation>){
        if (list.isEmpty())
            return
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserAdapter.ViewHolder {
        return ViewHolder(ItemListUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ListUserAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(private val binding:ItemListUserBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:UserEntityPresentation){
            binding.run {
                txtUser.text = data.name
                txtAddress.text = data.address
                txtCity.text = data.city
                txtEmail.text = data.email
                txtPhone.text = data.phoneNumber

                crdList.setOnClickListener {
                    if (expandMoreinfo.isExpanded)
                        expandMoreinfo.collapse()
                    else
                        expandMoreinfo.expand()
                }

            }
        }
    }

}