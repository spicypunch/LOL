package com.example.lol.patter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lol.RecyclerViewAdapter

import com.example.lol.databinding.ActivityMainBinding
import com.example.lol.retrofit.LOLResponse.LOLResponseItem
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainContract.View {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val adapter by lazy { RecyclerViewAdapter() }
    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        presenter.getAPI()
    }

    override fun getItem(response: Response<List<LOLResponseItem>>) {
        if (response.isSuccessful) {
            Toast.makeText(this@MainActivity, "데이터 가져오기 성공!", Toast.LENGTH_SHORT).show()
            response.body()?.let { setUI(it as ArrayList<LOLResponseItem>) }
        } else {
            Toast.makeText(this@MainActivity, "데이터 가져오기 실패...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUI(lolData: ArrayList<LOLResponseItem>) {
        adapter.updateList(lolData)
        adapter.notifyDataSetChanged()
    }
}

