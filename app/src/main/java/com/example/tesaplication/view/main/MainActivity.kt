package com.example.tesaplication.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tesaplication.R
import com.example.tesaplication.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel:MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getListUser()

        viewModelEvent()
    }

    private fun viewModelEvent() {
        viewModel.dataListUser.observe(this){data->
            if (data!=null && data.isNotEmpty()){
                binding.txtText.text= "ukuran data ${data.size}"
            }
        }
    }
}