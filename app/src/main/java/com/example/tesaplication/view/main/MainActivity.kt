package com.example.tesaplication.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tesaplication.databinding.ActivityMainBinding
import com.example.tesaplication.view.main.adapter.ListUserAdapter
import com.example.tesaplication.view.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModel()
    
    var sumUser = 0
    var sumCity = 0

    val mAdapter = ListUserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.rvUser){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        viewModel.getListUser()
        viewModel.getListCity()

        viewModelEvent()
    }

    private fun viewModelEvent() {
        viewModel.dataListUser.observe(this){data->
            if (data!=null && data.isNotEmpty()){
                sumUser = data.size
                //binding.txtText.text= "jumlah user ${sumUser} \njumlah kota ${sumCity}"
                mAdapter.setData(data)
            }
        }
        viewModel.dataListCity.observe(this){data->
            if (data!=null && data.isNotEmpty()){
                sumCity = data.size
                //binding.txtText.text= "jumlah user ${sumUser} \njumlah kota ${sumCity}"
            }
        }
        
    }
}