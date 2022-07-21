package com.github.derleymad.youflix

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.github.derleymad.youflix.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val dataList: List<Movie>,
    @LayoutRes private var layoutItem: Int
) : RecyclerView.Adapter<MovieAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutItem, parent, false)
//        val view = layoutInflater.inflate(R.layout.movie_item,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(currentItem: Movie) {
            val movieImg = itemView.findViewById<ImageView>(R.id.img)
            Picasso.get()
                .load(currentItem.coverUrl)
                .into(movieImg)
        }
    }
}