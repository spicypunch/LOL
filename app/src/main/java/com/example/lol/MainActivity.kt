package com.example.lol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lol.databinding.ActivityMainBinding
import com.example.lol.db.LOLEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lolList: LOLEntity
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getItem()
    }

    private fun getItem() {
        val lolList = (0..100).map {
            val index = (1..100).random()
            LOLEntity(champName = "가렌", champLevel = index, champPoints = index)
        }
//        val startNum = adapter.itemCount
        adapter = RecyclerViewAdapter(lolList as ArrayList<LOLEntity>)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.updateList(lolList)
        adapter.notifyDataSetChanged()
//        adapter.notifyItemRangeInserted(startNum, newList.size)
    }

    override fun onRestart() {
        super.onRestart()
        getItem()
    }
}