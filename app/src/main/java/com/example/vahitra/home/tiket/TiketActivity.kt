package com.example.vahitra.home.tiket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vahitra.R
import com.example.vahitra.model.Checkout
import com.example.vahitra.model.Film
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TiketActivity : AppCompatActivity() {

    lateinit var tv_title: TextView
    lateinit var tv_genre: TextView
    lateinit var tv_rate: TextView
    lateinit var iv_poster_image: ImageView
    lateinit var rv_checkout: RecyclerView

    private var dataList = ArrayList<Checkout>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket2)

        tv_title = findViewById(R.id.tv_judul_tiket)
        tv_genre = findViewById(R.id.tv_genre_tiket)
        tv_rate = findViewById(R.id.tv_rate_tiket)
        iv_poster_image = findViewById(R.id.iv_poster_image)
        rv_checkout = findViewById(R.id.rv_checkout)

        val data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data!!.judul
        tv_genre.text = data.genre
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster_image)

        rv_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1",""))
        dataList.add(Checkout("C2",""))

        rv_checkout.adapter = TiketAdapter(dataList) {
        }

    }
}