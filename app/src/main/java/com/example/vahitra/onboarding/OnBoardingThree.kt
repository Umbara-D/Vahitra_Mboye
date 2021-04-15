package com.example.vahitra.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.vahitra.R
import com.example.vahitra.sign.SignInActivity

class OnBoardingThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)

        val button_next : Button = findViewById(R.id.button_next)

        button_next.setOnClickListener{

            var intent = Intent(this@OnBoardingThree, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}