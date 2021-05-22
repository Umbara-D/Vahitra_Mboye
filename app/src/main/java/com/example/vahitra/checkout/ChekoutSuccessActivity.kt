package com.example.vahitra.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.vahitra.R
import com.example.vahitra.home.HomeActivity
import com.example.vahitra.home.tiket.TiketActivity
import com.example.vahitra.home.tiket.TiketFragment

class ChekoutSuccessActivity : AppCompatActivity() {

    lateinit var btn_home: Button
    lateinit var btn_tiket: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chekout_success)

        btn_home = findViewById(R.id.btn_home)
        btn_tiket = findViewById(R.id.btn_tiket)

        btn_home.setOnClickListener{
            var home = Intent(this@ChekoutSuccessActivity, HomeActivity::class.java)
            startActivity(home)
        }

        btn_tiket.setOnClickListener {
            var tiket = Intent(this@ChekoutSuccessActivity, TiketActivity::class.java)
            startActivity(tiket)
        }
    }
}