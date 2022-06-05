package com.example.tesaplication.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tesaplication.R
import com.example.tesaplication.databinding.ActivityMainBinding
import com.example.tesaplication.util.ItemClickListener
import com.example.tesaplication.view.adduser.AddUserActivity
import com.example.tesaplication.view.main.adapter.ListCityAdapter
import com.example.tesaplication.view.main.adapter.ListUserAdapter
import com.example.tesaplication.view.main.model.CityEntityPresentation
import com.example.tesaplication.view.main.model.UserEntityPresentation
import com.example.tesaplication.view.main.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    companion object{
        var REQUEST_ADD_USER = 100
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModel()

    lateinit var bottomSheetFilter:ConstraintLayout
    var bottomSheetBehaviorFilter:BottomSheetBehavior<ConstraintLayout?>? = null
    var rvFilter:RecyclerView? = null

    var sumUser = 0
    var sumCity = 0

    val mAdapter = ListUserAdapter()
    var cityAdapter = ListCityAdapter()
    var txtSeacrh = ""
    var txtFilter = ""

    var listDataUser:ArrayList<UserEntityPresentation> = ArrayList()
    var listDataCity:ArrayList<CityEntityPresentation> = ArrayList()

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

        setBottomSheetFilter()
        initEvent()
        viewModelEvent()
    }

    private fun setBottomSheetFilter() {
        bottomSheetFilter = findViewById(R.id.bottomsheet_filter)
        rvFilter = findViewById(R.id.rv_filter)
        val bottomSheet = BottomSheetBehavior.from(bottomSheetFilter)
        bottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    binding.bg.visibility = View.INVISIBLE
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.bg.visibility = View.VISIBLE
                binding.bg.alpha = slideOffset
            }
        })
        bottomSheetBehaviorFilter = bottomSheet

        with(rvFilter){
            this?.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = cityAdapter
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEvent() {
        binding.run {
            imgAddUser.setOnClickListener {
                val intent = Intent(application,AddUserActivity::class.java)
                intent.putExtra(AddUserActivity.ARG_LIST_CITY,listDataCity)
                startActivityForResult(intent, REQUEST_ADD_USER)
            }

            imgFilter.setOnClickListener {

                val handler = Handler(Looper.getMainLooper())
                hideKeyboard()
                handler.postDelayed({
                    if (bottomSheetBehaviorFilter?.state == BottomSheetBehavior.STATE_COLLAPSED)
                        bottomSheetBehaviorFilter?.state = BottomSheetBehavior.STATE_EXPANDED
                },100)
            }

            edtSearch.setOnTouchListener(object : View.OnTouchListener{
                override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    bottomSheetBehaviorFilter?.state = BottomSheetBehavior.STATE_COLLAPSED
                    return false
                }
            })

            edtSearch.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }


                var isSearch = true

                override fun afterTextChanged(p0: Editable?) {
                    isSearch = true
                    imgClear.isVisible = true
                    if (p0.isNullOrEmpty()){
                        isSearch = false
                        imgClear.isVisible = false
                    }
                    txtSeacrh = p0.toString()
                    searchUser()
                }
            })

            imgClear.setOnClickListener {
                edtSearch.setText("")
            }

        }
    }

    private fun viewModelEvent() {
        viewModel.dataListUser.observe(this){data->
            if (data!=null && data.isNotEmpty()){
                listDataUser.clear()
                listDataUser.addAll(data)
                mAdapter.setData(listDataUser)
                searchUser()
            }
        }
        viewModel.dataListCity.observe(this){data->
            if (data!=null && data.isNotEmpty()){
                listDataCity.addAll(data)
                listDataCity.add(0,CityEntityPresentation(id = 100,name="All"))
                cityAdapter.setData(listDataCity)
                cityAdapter.setItemClickListener(object :ItemClickListener<CityEntityPresentation>{
                    override fun onClick(data: CityEntityPresentation) {
                        if (data.name!=null){
                            txtFilter = data.name.toLowerCase()
                            if (data.name=="All")
                                txtFilter = ""
                            searchUser()
                            bottomSheetBehaviorFilter?.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                })
            }
        }

        viewModel.isLoadingListUser.observe(this){status->
            binding.pbLoading.isVisible = status
        }
        
    }

    private fun searchUser(){
        var listData:ArrayList<UserEntityPresentation> = ArrayList()
        if (listDataUser.isNotEmpty()){
            for (item in listDataUser){
                if (item.city!=null && item.name!=null){
                    Log.d("SeacrhUser","Search $txtSeacrh City $txtFilter")
                    if (txtFilter!=""){
                        if (item.city.toLowerCase()==txtFilter  && item.name.toLowerCase().contains(txtSeacrh)){
                            listData.add(item)
                        }
                    }else{
                        if (item.name.toLowerCase().contains(txtSeacrh)){
                            listData.add(item)
                        }
                    }

                }
            }
        }
        mAdapter.setData(listData)
        binding.lytEmpty.isVisible = listData.isEmpty()
    }

    override fun onBackPressed() {
        if (bottomSheetBehaviorFilter?.state == BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehaviorFilter?.state = BottomSheetBehavior.STATE_COLLAPSED
        }else{
            super.onBackPressed()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== REQUEST_ADD_USER && resultCode== AddUserActivity.SUCCES_ADD_USER){
            viewModel.getListUser()
        }
    }

    fun hideKeyboard() =
        (this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager)!!
            .hideSoftInputFromWindow(binding.edtSearch.windowToken, 0)
}