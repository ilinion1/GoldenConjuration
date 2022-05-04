package com.rockstargames.gta.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rockstargames.gta.R
import com.rockstargames.gta.databinding.FragmentSettingBinding
import com.rockstargames.gta.di.DaggerApplicationComponent
import com.rockstargames.gta.presentation.GameViewModel
import javax.inject.Inject


class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding

    private val viewModel by lazy { ViewModelProvider(requireActivity())[GameViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerApplicationComponent.create().inject(this)
        if (viewModel.normLevel.value == true && viewModel.setImageSettings){
            binding.rdNormal.isChecked = true
        } else if (viewModel.normLevel.value == false && viewModel.setImageSettings) {
            binding.rdHard.isChecked = true
        }
        setImage()
        clickListener()
    }

    private fun clickListener(){
        binding.rdNormal.setOnClickListener {
            viewModel.normLevel.value = true
            setImage()
        }
        binding.rdHard.setOnClickListener {
            viewModel.normLevel.value = false
            setImage()
        }
    }

    private fun setImage(){
        if(!binding.rdNormal.isChecked && !binding.rdHard.isChecked){
            binding.imSetting.setImageResource(R.drawable.what)
        }
        if (binding.rdNormal.isChecked){
            binding.imSetting.setImageResource(R.drawable.normal)
            viewModel.setImageSettings = true
        }
        if (binding.rdHard.isChecked){
            binding.imSetting.setImageResource(R.drawable.hard)
            viewModel.setImageSettings = true
        }
    }
}