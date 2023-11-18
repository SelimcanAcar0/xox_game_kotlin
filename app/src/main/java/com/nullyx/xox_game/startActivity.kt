package com.nullyx.xox_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nullyx.xox_game.databinding.ActivityStartBinding

class startActivity : AppCompatActivity() {
    lateinit var binding:ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}