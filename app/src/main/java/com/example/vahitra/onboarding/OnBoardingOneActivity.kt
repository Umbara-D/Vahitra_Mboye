package com.example.vahitra.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.vahitra.R
import com.example.vahitra.sign.SignInActivity
import com.example.vahitra.utils.Preferences


class OnBoardingOneActivity : AppCompatActivity() {

    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        preference = Preferences(this)
        if(preference.getValues("onboarding").equals("1")){
            finishAffinity()

            var intent = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        val button: Button = findViewById(R.id.btn_home)
        button.setOnClickListener{
            preference.setValues("onboarding", "1")
            finishAffinity()
            var intent = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        val button1: Button = findViewById(R.id.btn_tiket)
        button1.setOnClickListener{


            var intent = Intent(this@OnBoardingOneActivity, onBoardingTwo::class.java)
            startActivity(intent)
        }


    }
}