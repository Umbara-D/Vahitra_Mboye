package com.example.vahitra.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vahitra.R
import com.example.vahitra.home.dashboard.PlaysAdapter
import com.example.vahitra.model.Film
import com.example.vahitra.model.Plays
import com.google.firebase.database.*
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private var datalist = ArrayList<Plays>()

    lateinit var poster: ImageView
    lateinit var judul: TextView
    lateinit var rating: TextView
    lateinit var deskripsi: TextView
    lateinit var genre: TextView
    lateinit var rv_artis: RecyclerView
    lateinit var btn_bangku: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        poster = findViewById(R.id.iv_film)
        judul = findViewById(R.id.tv_judul)
        rating = findViewById(R.id.tv_rating)
        deskripsi = findViewById(R.id.tv_deskripsi)
        genre = findViewById(R.id.tv_golongan)
        rv_artis = findViewById(R.id.rv_plays)
        btn_bangku = findViewById(R.id.btn_pilih_bangku)

        val data = intent.getParcelableExtra<Film>("data")
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
                .child(data!!.judul.toString())
                .child("play")

        judul.text = data.judul
        genre.text = data.genre
        rating.text = data.rating
        deskripsi.text = data.desc

        Glide.with(this)
                .load(data.poster)
                .into(poster)

        rv_artis.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_bangku.setOnClickListener{
            var bangku = Intent(this@DetailActivity, PilihBangkuActivity::class.java).putExtra("data", data)
            startActivity(bangku)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(DBerror: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+DBerror.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()

                for(getdataSnapshot in snapshot.children){
                    var Film = getdataSnapshot.getValue(Plays::class.java)
                    datalist.add(Film!!)
                }

                rv_artis.adapter = PlaysAdapter(datalist){

                }
            }


        })
    }
}