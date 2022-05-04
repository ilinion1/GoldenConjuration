package com.rockstargames.gta.di

import com.rockstargames.gta.presentation.fragment.GameFragment
import com.rockstargames.gta.presentation.fragment.ResultFragment
import com.rockstargames.gta.presentation.fragment.SettingFragment
import dagger.Component



@Component()
interface ApplicationComponent {

    fun inject(gameFragment: GameFragment)
    fun inject(settingFragment: SettingFragment)
    fun inject(resultFragment: ResultFragment)
}