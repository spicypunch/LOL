package com.example.lol.patter

import com.example.lol.retrofit.LOLResponse
import retrofit2.Response

interface MainContract {
    interface View {
        fun getItem(response: Response<List<LOLResponse.LOLResponseItem>>)
    }

    interface Presenter {
        fun getAPI()
    }
}