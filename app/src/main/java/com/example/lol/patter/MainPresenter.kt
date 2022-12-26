package com.example.lol.patter

import android.util.Log
import com.example.lol.retrofit.LOLResponse
import com.example.lol.retrofit.LOLService
import com.example.lol.retrofit.RetrofitConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun getAPI() {
        val retrofitAPI = RetrofitConnection.getInstance().create(LOLService::class.java)
        /*excute() 함수는 요청을 현재 쓰레드에서 처리하지만,
        enqueue() 함수는 백그라운드 스레드에서 요청을 수행한 후에 콜백은 현재 쓰레드에서 처리함 */
        retrofitAPI.getInformation(
            "RGAPI-c145a4cf-bb91-4749-8adf-3c6fb1bd9197"
        ).enqueue(object : Callback<List<LOLResponse.LOLResponseItem>> {
            override fun onResponse(
                call: Call<List<LOLResponse.LOLResponseItem>>,
                response: Response<List<LOLResponse.LOLResponseItem>>
            ) {
                view.getItem(response)
            }

            override fun onFailure(call: Call<List<LOLResponse.LOLResponseItem>>, t: Throwable) {
                Log.e("가져오기 실패", "실패?")
                t.printStackTrace()
            }
        })
    }
}