package com.example.vahitra.home.dashboard

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vahitra.R
import com.example.vahitra.model.Plays

class PlaysAdapter(private var data: List<Plays>,
                   private val listener:(Plays) -> Unit) :
    RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaysAdapter.ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_plays, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tv_nama: TextView = view.findViewById(R.id.tv_artis)
        private val iv_artis: ImageView = view.findViewById(R.id.iv_artis)

        fun bindItem(data: Plays, listener: (Plays) -> Unit, context: Context){
            tv_nama.setText(data.nama)

            Glide.with(context)
                .load(data.url)
                    .apply(RequestOptions.circleCropTransform())
                .into(iv_artis)

            itemView.setOnClickListener {
                listener(data)
            }

        }


    }

}
