package com.example.lol.patter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lol.R
import com.example.lol.RecyclerViewAdapter

import com.example.lol.databinding.ActivityMainBinding
import com.example.lol.retrofit.LOLResponse.LOLResponseItem

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter by lazy { RecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.loadUserInfo()

        viewModel.items.observe(this, Observer {
            (binding.recyclerView.adapter as RecyclerViewAdapter).updateList(it)
        })

        viewModel.fail.observe(this, Observer {
            if (viewModel.fail.value == true) {
                Toast.makeText(this, "API 요청에 실패했습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "가져오기 성공!", Toast.LENGTH_SHORT).show()
            }
        })

    }
}