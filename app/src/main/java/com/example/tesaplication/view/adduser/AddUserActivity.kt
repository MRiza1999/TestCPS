package com.example.tesaplication.view.adduser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tesaplication.R
import com.example.tesaplication.core.data.main.source.remote.request.RequestAddUser
import com.example.tesaplication.databinding.ActivityAddUserBinding
import com.example.tesaplication.databinding.ActivityMainBinding
import com.example.tesaplication.view.main.model.CityEntityPresentation
import com.example.tesaplication.view.main.viewmodel.MainViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import org.koin.android.viewmodel.ext.android.viewModel

class AddUserActivity : AppCompatActivity() {

    companion object{
        var SUCCES_ADD_USER = 200
        var ARG_LIST_CITY = "list_city"
    }

    private val binding by lazy { ActivityAddUserBinding.inflate(layoutInflater) }

    var listCity:ArrayList<String> = ArrayList()

    var loadingDialog: KProgressHUD? = null

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        loadingDialog = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(1)
            .setDimAmount(0.3f)

        if (intent.getParcelableArrayListExtra<CityEntityPresentation>(ARG_LIST_CITY) !=null){
            val data  = intent.getParcelableArrayListExtra<CityEntityPresentation>(ARG_LIST_CITY)
            data?.removeAt(0)
            for (item in data!!){
                if (item.name!=null){
                    listCity.add(item.name)
                }
            }
            binding.spnnrCity.adapter =
                ArrayAdapter<String>(this,R.layout.item_spinner,listCity)

        }

        initListener()
        viewModelEvent()

    }

    private fun viewModelEvent() {

        viewModel.isLoadingAddUser.observe(this){status->
            if (!status){
                loadingDialog?.dismiss()
            }
        }

        viewModel.dataAddUser.observe(this){data->
            if (data?.id != null){
                setResult(SUCCES_ADD_USER)
                finish()
            }
        }

    }

    private fun initListener() {

        binding.btnSave.setOnClickListener {
            if (allDataNotEmpty()){
                binding.run {
                    val mCity = spnnrCity.selectedItem.toString()
                    val requestAddUser = RequestAddUser(
                        name = edtFullname.text.toString(),
                        address = edtAddress.text.toString(),
                        phoneNumber = edtPhonenumber.text.toString(),
                        email = edtEmail.text.toString(),
                        city = mCity
                    )
                    loadingDialog?.show()
                    viewModel.postAddUser(requestAddUser)
                }
            }else{
                Toast.makeText(this, "Input All Data", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun allDataNotEmpty(): Boolean {
        var status = true

        binding.run {
            if (edtFullname.text.isNullOrEmpty())
                status = false
            else if (edtAddress.text.isNullOrEmpty())
                status = false
            else if (edtEmail.text.isNullOrEmpty())
                status = false
            else if (edtAddress.text.isNullOrEmpty())
                status = false

        }

        return status

    }
}