package com.rockstargames.gta.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rockstargames.gta.databinding.FragmentResultBinding
import com.rockstargames.gta.di.DaggerApplicationComponent
import com.rockstargames.gta.presentation.GameViewModel
import javax.inject.Inject

class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity())[GameViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerApplicationComponent.create().inject(this)
        if (viewModel.tvResult == 1) {
            binding.tvResult.text = "1/3"
            binding.tvResultCount.text = viewModel.gameBalls.toString()
        }
        if (viewModel.tvResult == 2){
            binding.tvResult.text = "2/3"
            binding.tvResultCount.text = viewModel.gameBalls.toString()
        }
        if (viewModel.tvResult == 3) {
            binding.tvResult.text = "3/3"
            binding.tvResultCount.text = viewModel.gameBalls.toString()
        }
        binding.btNext.setOnClickListener {
            if (viewModel.tvResult == 3 || viewModel.tvResult > 3) viewModel.tvResult = 0
            viewModel.tvResult++
            if (viewModel.tvResult == 1) {
                binding.tvResult.text = "1/3"
                binding.tvResultCount.text = viewModel.gameBalls.toString()
            }
            if (viewModel.tvResult == 2){
                binding.tvResult.text = "2/3"
                binding.tvResultCount.text = viewModel.gameBalls.toString()
            }
            if (viewModel.tvResult == 3) {
                binding.tvResult.text = "3/3"
                binding.tvResultCount.text = viewModel.gameBalls.toString()
            }
        }
    }

}