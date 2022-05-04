package com.rockstargames.gta.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rockstargames.gta.presentation.fragment.DescriptionFragment
import com.rockstargames.gta.presentation.fragment.GameFragment
import com.rockstargames.gta.presentation.fragment.ResultFragment
import com.rockstargames.gta.presentation.fragment.SettingFragment

class MenuGameAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> GameFragment()
            1 -> ResultFragment()
            2 -> SettingFragment()
            else -> DescriptionFragment()
        }
    }
}