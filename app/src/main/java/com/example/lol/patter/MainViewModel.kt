package com.example.lol.patter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lol.retrofit.LOLResponse.LOLResponseItem
import com.example.lol.retrofit.LOLService
import com.example.lol.retrofit.RetrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel() : ViewModel() {

    private val _items = MutableLiveData<ArrayList<LOLResponseItem>>()
    val items: LiveData<ArrayList<LOLResponseItem>>
        get() = _items

    private val _fail = MutableLiveData<Boolean>()
    val fail: LiveData<Boolean>
        get() = _fail

    fun loadUserInfo() {
        val retrofitAPI = RetrofitConnection.getInstance().create(LOLService::class.java)
        retrofitAPI.getInformation(
            //api 요청이 실패한다면 인증키 유효기간이 지났기 때문(인증키 유효기간 하루)
            "RGAPI-ed11860f-dd20-4abd-b24f-fa5168e82ae5"
        ).enqueue(object : Callback<List<LOLResponseItem>> {
            override fun onResponse(
                call: Call<List<LOLResponseItem>>,
                response: Response<List<LOLResponseItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _items.value = it as ArrayList<LOLResponseItem>
                    }
                } else {
                    _fail.value = true
                }
            }

            override fun onFailure(call: Call<List<LOLResponseItem>>, t: Throwable) {
                Log.e("가져오기 실패", "실패?")
                t.printStackTrace()
            }
        })
    }
}