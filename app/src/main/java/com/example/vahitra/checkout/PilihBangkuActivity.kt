package com.example.vahitra.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.vahitra.R
import com.example.vahitra.model.Checkout
import com.example.vahitra.model.Film
import java.util.ArrayList

class PilihBangkuActivity : AppCompatActivity() {

    lateinit var judul_film: TextView
    lateinit var btn_beli: Button
    lateinit var btn_back: ImageView

    var statusA1:Boolean = false
    var statusA2:Boolean = false
    var statusA3:Boolean = false
    var statusA4:Boolean = false
    var statusB1:Boolean = false
    var statusB2:Boolean = false
    var statusB3:Boolean = false
    var statusB4:Boolean = false
    var statusC1:Boolean = false
    var statusC2:Boolean = false
    var statusC3:Boolean = false
    var statusC4:Boolean = false
    var statusD1:Boolean = false
    var statusD2:Boolean = false
    var statusD3:Boolean = false
    var statusD4:Boolean = false
    var total:Int = 0

    lateinit var a1: ImageView
    lateinit var a2: ImageView
    lateinit var a3: ImageView
    lateinit var a4: ImageView
    lateinit var b1: ImageView
    lateinit var b2: ImageView
    lateinit var b3: ImageView
    lateinit var b4: ImageView
    lateinit var c1: ImageView
    lateinit var c2: ImageView
    lateinit var c3: ImageView
    lateinit var c4: ImageView
    lateinit var d1: ImageView
    lateinit var d2: ImageView
    lateinit var d3: ImageView
    lateinit var d4: ImageView

    private var datalist = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        judul_film = findViewById(R.id.tv_judul_film)
        btn_beli =  findViewById(R.id.btn_beli)
        btn_back = findViewById(R.id.btn_back)
        a1 = findViewById(R.id.a1)
        a2 = findViewById(R.id.a2)
        a3 = findViewById(R.id.a3)
        a4 = findViewById(R.id.a4)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        c1 = findViewById(R.id.c1)
        c2 = findViewById(R.id.c2)
        c3 = findViewById(R.id.c3)
        c4 = findViewById(R.id.c4)
        d1 = findViewById(R.id.d1)
        d2 = findViewById(R.id.d2)
        d3 = findViewById(R.id.d3)
        d4 = findViewById(R.id.d4)

        val data = intent.getParcelableExtra<Film>("data")
        judul_film.text = data!!.judul

        a1.setOnClickListener {
            if(statusA1){
                a1.setImageResource(R.drawable.ic_rectangle_21)
                statusA1 = false
                total -= 1
                belitiket(total)
            } else{
                a1.setImageResource(R.drawable.ic_rectangle_pilih)
                statusA1 = false
                total += 1
                belitiket(total)

                val data = Checkout("A1", "35000")
                datalist.add(data)
            }
        }

        a2.setOnClickListener {
            if(statusA2){
                a2.setImageResource(R.drawable.ic_rectangle_21)
                statusA2 = false
                total -= 1
                belitiket(total)
            } else{
                a2.setImageResource(R.drawable.ic_rectangle_pilih)
                statusA2 = false
                total += 1
                belitiket(total)

                val data = Checkout("A2", "35000")
                datalist.add(data)
            }
        }

        a3.setOnClickListener {
            if(statusA3){
                a4.setImageResource(R.drawable.ic_rectangle_21)
                statusA3 = false
                total -= 1
                belitiket(total)
            } else{
                a3.setImageResource(R.drawable.ic_rectangle_pilih)
                statusA3 = false
                total += 1
                belitiket(total)

                val data = Checkout("A3", "35000")
                datalist.add(data)
            }
        }

        a4.setOnClickListener {
            if(statusA4){
                a1.setImageResource(R.drawable.ic_rectangle_21)
                statusA4 = false
                total -= 1
                belitiket(total)
            } else{
                a4.setImageResource(R.drawable.ic_rectangle_pilih)
                statusA4 = false
                total += 1
                belitiket(total)

                val data = Checkout("A4", "35000")
                datalist.add(data)
            }
        }

        b1.setOnClickListener {
            if(statusB1){
                b1.setImageResource(R.drawable.ic_rectangle_21)
                statusB1 = false
                total -= 1
                belitiket(total)
            } else{
                b1.setImageResource(R.drawable.ic_rectangle_pilih)
                statusB1 = false
                total += 1
                belitiket(total)

                val data = Checkout("B1", "35000")
                datalist.add(data)
            }
        }

        b2.setOnClickListener {
            if(statusB2){
                b2.setImageResource(R.drawable.ic_rectangle_21)
                statusB2 = false
                total -= 1
                belitiket(total)
            } else{
                b2.setImageResource(R.drawable.ic_rectangle_pilih)
                statusB2 = false
                total += 1
                belitiket(total)

                val data = Checkout("B2", "35000")
                datalist.add(data)
            }
        }

        b3.setOnClickListener {
            if(statusB3){
                b3.setImageResource(R.drawable.ic_rectangle_21)
                statusB3 = false
                total -= 1
                belitiket(total)
            } else{
                b3.setImageResource(R.drawable.ic_rectangle_pilih)
                statusB3 = false
                total += 1
                belitiket(total)

                val data = Checkout("B3", "35000")
                datalist.add(data)
            }
        }

        b4.setOnClickListener {
            if(statusB4){
                b4.setImageResource(R.drawable.ic_rectangle_21)
                statusB4 = false
                total -= 1
                belitiket(total)
            } else{
                b4.setImageResource(R.drawable.ic_rectangle_pilih)
                statusB4 = false
                total += 1
                belitiket(total)

                val data = Checkout("B4", "35000")
                datalist.add(data)
            }
        }

        c1.setOnClickListener {
            if(statusC1){
                c1.setImageResource(R.drawable.ic_rectangle_21)
                statusC1 = false
                total -= 1
                belitiket(total)
            } else{
                c1.setImageResource(R.drawable.ic_rectangle_pilih)
                statusC1 = false
                total += 1
                belitiket(total)

                val data = Checkout("C1", "35000")
                datalist.add(data)
            }
        }
        
        c2.setOnClickListener {
            if(statusC2){
                c2.setImageResource(R.drawable.ic_rectangle_21)
                statusC2 = false
                total -= 1
                belitiket(total)
            } else{
                c2.setImageResource(R.drawable.ic_rectangle_pilih)
                statusC2 = false
                total += 1
                belitiket(total)

                val data = Checkout("C2", "35000")
                datalist.add(data)
            }
        }

        c3.setOnClickListener {
            if(statusC3){
                c3.setImageResource(R.drawable.ic_rectangle_21)
                statusC3 = false
                total -= 1
                belitiket(total)
            } else{
                c3.setImageResource(R.drawable.ic_rectangle_pilih)
                statusC3 = false
                total += 1
                belitiket(total)

                val data = Checkout("C3", "35000")
                datalist.add(data)
            }
        }

        c4.setOnClickListener {
            if(statusC4){
                c4.setImageResource(R.drawable.ic_rectangle_21)
                statusC4 = false
                total -= 1
                belitiket(total)
            } else{
                c4.setImageResource(R.drawable.ic_rectangle_pilih)
                statusC4 = false
                total += 1
                belitiket(total)

                val data = Checkout("C4", "35000")
                datalist.add(data)
            }
        }

        d1.setOnClickListener {
            if(statusD1){
                d1.setImageResource(R.drawable.ic_rectangle_21)
                statusD1 = false
                total -= 1
                belitiket(total)
            } else{
                d1.setImageResource(R.drawable.ic_rectangle_pilih)
                statusD1 = false
                total += 1
                belitiket(total)

                val data = Checkout("D1", "350000")
                datalist.add(data)
            }
        }

        d2.setOnClickListener {
            if(statusD2){
                d2.setImageResource(R.drawable.ic_rectangle_21)
                statusD2 = false
                total -= 1
                belitiket(total)
            } else{
                d2.setImageResource(R.drawable.ic_rectangle_pilih)
                statusD2 = false
                total += 1
                belitiket(total)

                val data = Checkout("D2", "350000")
                datalist.add(data)
            }
        }

        d3.setOnClickListener {
            if(statusD3){
                d3.setImageResource(R.drawable.ic_rectangle_21)
                statusD3 = false
                total -= 1
                belitiket(total)
            } else{
                d3.setImageResource(R.drawable.ic_rectangle_pilih)
                statusD4 = false
                total += 1
                belitiket(total)

                val data = Checkout("D3", "350000")
                datalist.add(data)
            }
        }

        d4.setOnClickListener {
            if(statusD4){
                d4.setImageResource(R.drawable.ic_rectangle_21)
                statusD4 = false
                total -= 1
                belitiket(total)
            } else{
                d4.setImageResource(R.drawable.ic_rectangle_pilih)
                statusD4 = false
                total += 1
                belitiket(total)

                val data = Checkout("D4", "350000")
                datalist.add(data)
            }
        }

        btn_back.setOnClickListener {
            var back =  Intent(this@PilihBangkuActivity, DetailActivity::class.java)
            startActivity(back)
        }
        btn_beli.setOnClickListener {
            var pesan =  Intent(this@PilihBangkuActivity, CheckoutActivity::class.java)
                    .putExtra("data", datalist)
            startActivity(pesan)
        }


    }

    private fun belitiket(total: Int) {
        if(total==0){
            btn_beli.setText("Beli Tiket")
            btn_beli.visibility =  View.INVISIBLE
        } else{
            btn_beli.setText("Beli Tiket ("+total+")")
            btn_beli.visibility = View.VISIBLE
        }
    }
}