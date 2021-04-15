package com.example.vahitra.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.vahitra.R

class onBoardingTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)

        val button_next : Button = findViewById(R.id.button_next)
        button_next.setOnClickListener{
            var intent = Intent(this@onBoardingTwo, OnBoardingThree::class.java)
            startActivity(intent)
        }

    }
}