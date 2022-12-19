package com.example.lol.retrofit

class LOLResponse : ArrayList<LOLResponse.LOLResponseItem>() {
    data class LOLResponseItem(
        val championId: Int,
        val championLevel: Int,
        val championPoints: Int,
        val lastPlayTime: Long,
        val championPointsSinceLastLevel: Int,
        val championPointsUntilNextLevel: Int,
        val chestGranted: Boolean,
        val tokensEarned: Int,
        val summonerId: String
//        val championId: Int,
//        val championLevel: Int,
//        val championPoints: Int,
//        val championPointsSinceLastLevel: Int,
//        val championPointsUntilNextLevel: Int,
//        val chestGranted: Boolean,
//        val lastPlayTime: Long,
//        val summonerId: String,
//        val tokensEarned: Int
    )
}
