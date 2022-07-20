package com.github.derleymad.youflix

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.derleymad.youflix.model.Category
import com.github.derleymad.youflix.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Teste", "onCreate")

        val movies = mutableListOf<Movie>()
        for (i in 0..20) {
            movies.add(
                Movie(
                    img = R.drawable.movie,
                    id = i
                )
            )
        }

        val categories = mutableListOf<Category>()
        for (j in 0..5) {
            categories.add(Category("Categoria $j", movies))
        }

        recyclerView = findViewById(R.id.rv_main)
        val adapter = CategoryAdapter(categories)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

    }


}