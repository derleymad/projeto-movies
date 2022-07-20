package com.github.derleymad.youflix

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.derleymad.youflix.model.Category

class CategoryAdapter(private val dataList: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
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
        fun bind(currentItem: Category) {

            val categoryTitle = itemView.findViewById<TextView>(R.id.tv_category_title)
            val rvCategory: RecyclerView = itemView.findViewById(R.id.rv_category)

            categoryTitle.text = currentItem.name

            val adapter = MovieAdapter(currentItem.movies, R.layout.movie_item) { id ->
                itemView.context.startActivity(Intent(itemView.context, MovieActivity::class.java))
            }
            rvCategory.adapter = adapter
            rvCategory.layoutManager =
                LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)

        }
    }
}