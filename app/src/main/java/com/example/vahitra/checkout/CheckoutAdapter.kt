package com.example.vahitra.checkout

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vahitra.R
import com.example.vahitra.model.Checkout
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener:(Checkout) -> Unit) :
    RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutAdapter.ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tv_kursi: TextView = view.findViewById(R.id.tv_kursi)
        private val tv_harga: TextView = view.findViewById(R.id.tv_harga)


        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context: Context){

            val localID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tv_harga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if(data.kursi!!.startsWith("Total")){
                tv_kursi.setText(data.kursi)
                tv_kursi.setCompoundDrawables(null, null, null, null)
            }else{
                tv_kursi.setText("Kursi No."+data.kursi)
            }


            itemView.setOnClickListener {
                listener(data)
            }

        }


    }

}
