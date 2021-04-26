package com.example.vahitra.home.dashboard

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vahitra.R
import com.example.vahitra.model.Film

class TayangAdapter(private var data: List<Film>,
                    private val listener:(Film) -> Unit) :
    RecyclerView.Adapter<TayangAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TayangAdapter.ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_tayang, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TayangAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tvTitle: TextView = view.findViewById(R.id.tv_kursi)
        private val tvGenre: TextView = view.findViewById(R.id.tv_artis)
        private val tvRating: TextView = view.findViewById(R.id.tv_rate_tiket)
        private val tvImage: ImageView = view.findViewById(R.id.iv_artis)

        fun bindItem(data: Film, listener: (Film) -> Unit, context: Context){
            tvTitle.setText(data.judul)
            tvGenre.setText(data.genre)
            tvRating.setText(data.rating)

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }

        }


    }

}
