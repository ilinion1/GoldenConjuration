package com.rockstargames.gta.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rockstargames.gta.R

class GameViewModel: ViewModel() {
    var normLevel = MutableLiveData(true)
    var setImageSettings = false
    var tvResult = 1
    var gameBalls = 0
    var gameLives = if (normLevel.value == true) 8 else 6
    val startGameList = listOf(R.drawable.game,R.drawable.game,R.drawable.game,R.drawable.game,
        R.drawable.game,R.drawable.game,R.drawable.game,R.drawable.game,)
    val gameList = listOf(R.drawable.game_lose1,R.drawable.game_win1,R.drawable.game_lose2,
        R.drawable.game_win2, R.drawable.game_lose3, R.drawable.game_win3,
        R.drawable.game_lose4, R.drawable.game_win4)
}