package com.example.vahitra.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vahitra.R
import com.example.vahitra.model.Checkout
import com.example.vahitra.utils.Preferences

class CheckoutActivity : AppCompatActivity() {

    private var datalist =  ArrayList<Checkout>()
    private var total:Int = 0
    private lateinit var preferences: Preferences

    private lateinit var rv_pesanan: RecyclerView
    private lateinit var tv_total: TextView
    lateinit var btn_back: ImageView
    lateinit var saldo: TextView
    lateinit var btn_bayar: Button
    lateinit var btn_cancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        rv_pesanan = findViewById(R.id.rv_pesanan)
        tv_total = findViewById(R.id.tv_total)
        btn_back = findViewById(R.id.btn_back)
        btn_bayar = findViewById(R.id.btn_tiket)
        btn_cancel = findViewById(R.id.btn_home)


        preferences = Preferences(this)
        datalist = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for(a in datalist.indices){
            total += datalist[a].harga!!.toInt()
        }

        datalist.add(Checkout("Total Harus Dibayar", total.toString()))

        rv_pesanan.layoutManager = LinearLayoutManager(this)
        rv_pesanan.adapter = CheckoutAdapter(datalist){

        }

        btn_bayar.setOnClickListener{
            finishAffinity()
            var bayar = Intent(this@CheckoutActivity, ChekoutSuccessActivity::class.java)
            startActivity(bayar)
        }

        btn_back.setOnClickListener{
            var bayar = Intent(this@CheckoutActivity, PilihBangkuActivity::class.java)
            startActivity(bayar)
        }

        btn_cancel.setOnClickListener{
            var bayar = Intent(this@CheckoutActivity, PilihBangkuActivity::class.java)
            startActivity(bayar)
        }
    }
}