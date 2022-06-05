package com.example.tesaplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tesaplication.view.main.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val viewModel:MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getListUser()


        viewModelEvent()

    }

    private fun viewModelEvent() {
        viewModel.dataListUser.observe(this){data->
            if (data!=null && data.size>0){
                Toast.makeText(applicationContext, "ukuran data ${data.size}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}