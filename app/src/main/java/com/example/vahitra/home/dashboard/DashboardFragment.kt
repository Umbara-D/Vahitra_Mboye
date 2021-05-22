package com.example.vahitra.home.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vahitra.R
import com.example.vahitra.checkout.DetailActivity
import com.example.vahitra.model.Film
import com.example.vahitra.utils.Preferences
import com.google.firebase.database.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class DashboardFragment : Fragment() {

    lateinit var tv_nama: TextView
    lateinit var tv_saldo: TextView
    lateinit var iv_profile: ImageView
    lateinit var rv_tayang: RecyclerView
    lateinit var rv_segera: RecyclerView

    lateinit var preferences: Preferences
    lateinit var database: DatabaseReference
    private var datalist = ArrayList<Film>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = Preferences(activity!!.applicationContext)
        database = FirebaseDatabase.getInstance().getReference("Film")
        tv_nama = view.findViewById(R.id.tv_nama) as TextView
        tv_saldo = view.findViewById(R.id.tv_saldo) as TextView
        iv_profile = view.findViewById(R.id.iv_profile) as ImageView
        rv_tayang = view.findViewById(R.id.rv_tayang) as RecyclerView
        rv_segera = view.findViewById(R.id.rv_segera) as RecyclerView


        tv_nama.setText(preferences.getValues("nama"))

        //kondisi baru
        preferences.getValues("saldo")?.let {
            currency(it.toDouble(), tv_saldo)
        }

        /*
        kondisi lawas
        if(preferences.getValues("saldo").equals("")){
            currency(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        }
        */

        Glide.with(this)
                .load(preferences.getValues("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)

        rv_tayang.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_segera.layoutManager = LinearLayoutManager(context)

        getData()
    }


    private fun getData() {
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for(getDataSnapshot in snapshot.children){
                    var film = getDataSnapshot.getValue(Film::class.java)
                    datalist.add(film!!)
                }
                rv_tayang.adapter = TayangAdapter(datalist){
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
                rv_segera.adapter = SegeraAdapter(datalist){
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(databaseerror: DatabaseError) {
                Toast.makeText(context, " "+databaseerror.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun currency(harga: Double, textView: TextView) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        preferences = Preferences(activity!!.applicationContext)
//        database = FirebaseDatabase.getInstance().getReference("Film")
//        tv_nama = view!!.findViewById(R.id.tv_nama) as TextView
//        tv_saldo = view!!.findViewById(R.id.tv_saldo) as TextView
//        iv_profile = view!!.findViewById(R.id.iv_profile) as ImageView
//        rv_tayang = view!!.findViewById(R.id.rv_tayang) as RecyclerView
//        rv_segera = view!!.findViewById(R.id.rv_segera) as RecyclerView
//
//        tv_nama.setText(preferences.getValues("nama"))
//        if(preferences.getValues("saldo").equals("")){
//            currency(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
//        }
//
//        Glide.with(this)
//                .load(preferences.getValues("url"))
//                .apply(RequestOptions.circleCropTransform())
//                .into(iv_profile)
//
//        rv_tayang.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        rv_segera.layoutManager = LinearLayoutManager(context)
//
//        getData()
//    }

}