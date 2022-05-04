package com.rockstargames.gta.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.rockstargames.gta.R
import com.rockstargames.gta.databinding.FragmentGameBinding
import com.rockstargames.gta.di.DaggerApplicationComponent
import com.rockstargames.gta.presentation.GameViewModel
import com.rockstargames.gta.presentation.adapters.GameAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class GameFragment : Fragment(), GameAdapter.GameClickItem {
    private lateinit var binding: FragmentGameBinding
    lateinit var adapter: GameAdapter

    private val viewModel by lazy { ViewModelProvider(requireActivity())[GameViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerApplicationComponent.create().inject(this)
        binding.tvLives.text = resources.getString(R.string.lives, viewModel.gameLives.toString())
        binding.tvPoints.text = resources.getString(R.string.balls, viewModel.gameBalls.toString())
        setAdapter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.normLevel.observe(this){
          if (it){
              binding.tvLives.text = resources.getString(R.string.lives, "8")
          } else {
              binding.tvLives.text = resources.getString(R.string.lives, "6")
          }

        }

    }

    private fun setAdapter() {
        adapter = GameAdapter(this)
        binding.rcViewId.adapter = adapter
        binding.rcViewId.layoutManager = GridLayoutManager(context, 2)
        adapter.gameList.addAll(viewModel.startGameList)

    }

    override fun onClick(position: Int) {
        if (checkDoubleClick(position)) {
            if (checkStopGame()) {
                val newList = viewModel.gameList[(0..7).random()]
                adapter.gameList.removeAt(position)
                adapter.gameList.add(position, newList)
                adapter.notifyItemChanged(position)
                setBalls(newList)
            } else {
                lifecycleScope.launch {
                    delay(1000)
                    Toast.makeText(context, "New Game", Toast.LENGTH_SHORT).show()
                }
                val newList = viewModel.gameList[(0..7).random()]
                setBalls(newList)
                binding.tvPoints.text = resources.getString(R.string.balls, "0")
                adapter.gameList.clear()
                adapter.gameList.addAll(viewModel.startGameList)
                adapter.notifyItemRangeChanged(0, 7)
            }
        }

    }

    private fun setBalls(newList: Int) {
        viewModel.normLevel.observe(this) {
            if (viewModel.gameLives == 1) {
                if (it) {
                    viewModel.gameLives = 9
                } else {
                    viewModel.gameLives = 7
                }
            }
            viewModel.tvResult++
            if (viewModel.tvResult == 3) viewModel.tvResult = 0
        }
        if (newList == R.drawable.game_win1 || newList == R.drawable.game_win2 ||
            newList == R.drawable.game_win3 || newList == R.drawable.game_win4
        ) {
            viewModel.gameLives--
            binding.tvLives.text =
                resources.getString(R.string.lives, viewModel.gameLives.toString())
            viewModel.gameBalls += 10
            binding.tvPoints.text =
                resources.getString(R.string.balls, viewModel.gameBalls.toString())
        } else {
            viewModel.gameLives--
            binding.tvLives.text =
                resources.getString(R.string.lives, viewModel.gameLives.toString())
        }
    }

    private fun checkStopGame(): Boolean {
        if (viewModel.gameLives == 1) {
            Toast.makeText(
                context,
                "Good Game, Your Result ${viewModel.gameBalls}",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else return true
    }

    private fun checkDoubleClick(position: Int): Boolean {
        if (adapter.gameList[position] == viewModel.startGameList[0]) {
            return true
        } else {
            return false
        }
    }

}