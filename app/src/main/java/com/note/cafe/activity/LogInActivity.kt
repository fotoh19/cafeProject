package com.note.cafe.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.note.cafe.R
import com.note.cafe.databinding.ActivityLogInBinding

class LogInActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivityLogInBinding
    private var logInInfo = LogInInfo()
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = findViewById(R.id.UserName)
        password = findViewById(R.id.Password)
        login = findViewById(R.id.loginBtn)

        login.setOnClickListener(this)




        binding.skipBtn.setOnClickListener {
            startActivity(Intent(this@LogInActivity, MainActivity::class.java))

        }
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this@LogInActivity, IntroActivity::class.java))

        }
    }

    override fun onClick(v: View?) {
        if (logInInfo.Verify(
                username.text.toString(),
                password.text.toString()
            )
        ) {
            Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "login feild", Toast.LENGTH_SHORT).show()

        }
    }
}