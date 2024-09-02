package com.note.cafe.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.note.cafe.R
import com.note.cafe.databinding.IntroMainBinding



class IntroActivity : AppCompatActivity() {
    private var binding: IntroMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntroMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val startbtn = binding?.startbtn

        startbtn?.setOnClickListener {
            val navController = findNavController(R.id.logInhost)
            navController.navigate(R.id.action_IntroActivity_to_logInFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
