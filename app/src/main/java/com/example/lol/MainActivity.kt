package com.example.lol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lol.databinding.ActivityMainBinding
import com.example.lol.retrofit.LOLResponse.LOLResponseItem
import com.example.lol.retrofit.LOLService
import com.example.lol.retrofit.RetrofitConnection

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { RecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        getItem()
    }

    private fun getItem() {
        val retrofitAPI = RetrofitConnection.getInstance().create(LOLService::class.java)
        retrofitAPI.getInformation(
            "암호화된 ID 기입"
        ).enqueue(object : Callback<List<LOLResponseItem>> {
            override fun onResponse(
                call: Call<List<LOLResponseItem>>,
                response: Response<List<LOLResponseItem>>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "데이터 가져오기 성공!", Toast.LENGTH_SHORT).show()
                    response.body()?.let { setUI(it as ArrayList<LOLResponseItem>) }
                } else {
                    Toast.makeText(this@MainActivity, "데이터 가져오기 실패...", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<LOLResponseItem>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun setUI(lolData: ArrayList<LOLResponseItem>) {
        adapter.updateList(lolData)
    }
}

