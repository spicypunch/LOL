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

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private var List = arrayListOf<LOLResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView
        getItem()
//        List.add(LOLResponseItem(1, 2, 3, 4, 5,  6, true, 7, "종마루"))
//        setUI(List)
    }

    private fun getItem() {
        val retrofitAPI = RetrofitConnection.getInstance().create(LOLService::class.java)
        /*excute() 함수는 요청을 현재 쓰레드에서 처리하지만,
        enqueue() 함수는 백그라운드 스레드에서 요청을 수행한 후에 콜백은 현재 쓰레드에서 처리함 */
        retrofitAPI.getInformation(
            "RGAPI-ddbd4ed6-a076-4bfe-80ad-10c5f9fa615a"
        ).enqueue(object : Callback<List<LOLResponseItem>> {
            override fun onResponse(
                call: Call<List<LOLResponseItem>>,
                response: Response<List<LOLResponseItem>>
            ) {
                Log.e("response 잘 나오나?", "여기까지 호출 됨 $response")
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "데이터 가져오기 성공!", Toast.LENGTH_SHORT).show()
                    response.body()?.let { setUI(it as ArrayList<LOLResponseItem>) }
                } else {
                    Toast.makeText(this@MainActivity, "데이터 가져오기 실패...", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<LOLResponseItem>>, t: Throwable) {
                Log.e("가져오기 실패", "실패?")
                t.printStackTrace()
            }
        })
    }

    private fun setUI(lolData: ArrayList<LOLResponseItem>) {
        Log.e("loldata", lolData.toString())
        Thread {
            runOnUiThread {
                adapter = RecyclerViewAdapter(lolData)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
            }
        }.start()
    }

    override fun onRestart() {
        super.onRestart()
//        getItem()
    }
}

