package com.note.cafe.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.note.cafe.R
import com.note.cafe.databinding.IntroMainBinding


class IntroActivity : BaseActivity() {
    private var binding: IntroMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntroMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val startbtn = findViewById<Button>(R.id.startbtn)

        startbtn.setOnClickListener{
            findNavController(R.id.nav_graph).navigate(R.id.action_IntroActivity_to_logInFragment)
        }
    } }
