package com.example.lol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lol.databinding.ActivityMainBinding
import com.example.lol.retrofit.LOLResponseItem
import com.example.lol.retrofit.LOLService
import com.example.lol.retrofit.RetrofitConnection
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getItem()
    }

    private fun getItem() {

        val retrofitAPI = RetrofitConnection.getInstance().create(LOLService::class.java)

        /*excute() 함수는 요청을 현재 쓰레드에서 처리하지만,
        enqueue() 함수는 백그라운드 스레드에서 요청을 수행한 후에 콜백은 현재 쓰레드에서 처리함 */
        retrofitAPI.getInformation(
            "RGAPI-45fa8f07-1ae7-4f9a-b0f5-3153f54ed348"
        ).enqueue(object : retrofit2.Callback<LOLResponseItem> {
            override fun onResponse(
                call: Call<LOLResponseItem>,
                response: Response<LOLResponseItem>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "데이터 가져오기 성공!", Toast.LENGTH_SHORT).show()
                    response.body()?.let { setUI(it) }
                } else {
                    Toast.makeText(this@MainActivity, "데이터 가져오기 실패!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LOLResponseItem>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun setUI(lolData: LOLResponseItem) {
        Thread {
            runOnUiThread {
                adapter = RecyclerViewAdapter(lolData as MutableList<LOLResponseItem>)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
//                adapter.updateList(lolData)
//                adapter.notifyDataSetChanged()
            }
        }.start()
    }

    override fun onRestart() {
        super.onRestart()
        getItem()
    }

}

