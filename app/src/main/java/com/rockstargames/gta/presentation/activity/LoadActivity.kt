package com.rockstargames.gta.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.rockstargames.gta.R
import com.rockstargames.gta.databinding.ActivityLoadBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@LoadActivity, GameActivity::class.java))
        }
    }
}