package com.example.lol

import androidx.recyclerview.widget.DiffUtil
import com.example.lol.retrofit.LOLResponse

class DiffUtilCallback(
    private val oldList: MutableList<LOLResponse.LOLResponseItem>,
    private val newList: MutableList<LOLResponse.LOLResponseItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].championId == newList[newItemPosition].championId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}