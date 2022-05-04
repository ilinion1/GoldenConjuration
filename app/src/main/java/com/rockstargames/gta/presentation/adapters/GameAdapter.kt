package com.rockstargames.gta.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rockstargames.gta.R

class GameAdapter(private val gameClickItem: GameClickItem ): RecyclerView.Adapter<GameAdapter.GameHolder>() {
    var gameList = arrayListOf<Int>()

    interface GameClickItem{
        fun onClick(position: Int)
    }

    class GameHolder(view: View): RecyclerView.ViewHolder(view) {
        val imGame = view.findViewById<ImageView>(R.id.imGame)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return GameHolder(view)
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.imGame.setImageResource(gameList[position])
        holder.itemView.setOnClickListener {
            gameClickItem.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return gameList.size
    }
}