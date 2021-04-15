package com.example.vahitra.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.vahitra.R
import com.example.vahitra.home.dashboard.DashboardFragment
import com.example.vahitra.home.tiket.TiketFragment

class HomeActivity : AppCompatActivity() {


    lateinit var menu1: ImageView
    lateinit var menu2: ImageView
    lateinit var menu3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        menu1 = findViewById(R.id.menu1)
        menu2 = findViewById(R.id.menu2)
        menu3 = findViewById(R.id.menu3)

        val fragmentHome = DashboardFragment()
        val fragmentTiket = TiketFragment()
        val fragmentSetting = SettingFragment()

        setFragment(fragmentHome)

        menu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(menu1, R.drawable.ic_home_active)
            changeIcon(menu2, R.drawable.ic_nav_profile)
            changeIcon(menu3, R.drawable.ic_tiket)
        }

        menu2.setOnClickListener {

            setFragment(fragmentSetting)

            changeIcon(menu1, R.drawable.ic_home)
            changeIcon(menu2, R.drawable.ic_profile_active)
            changeIcon(menu3, R.drawable.ic_tiket)
        }

        menu3.setOnClickListener {
            setFragment(fragmentTiket)

            changeIcon(menu1, R.drawable.ic_home)
            changeIcon(menu2, R.drawable.ic_nav_profile)
            changeIcon(menu3, R.drawable.ic_tiket_active)
        }

    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }
}