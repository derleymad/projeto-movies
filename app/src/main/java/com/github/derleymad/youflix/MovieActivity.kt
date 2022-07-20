package com.github.derleymad.youflix

import android.content.Intent
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.derleymad.youflix.model.Movie

class MovieActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTitle: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvCast: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val movies = mutableListOf<Movie>()
        for (i in 0..20) {
            movies.add(
                Movie(
                    img = R.drawable.movie,
                    id = i
                )
            )
        }
        tvTitle = findViewById(R.id.movie_txt_title)
        tvCast = findViewById(R.id.movie_txt_cast)
        tvDesc = findViewById(R.id.movie_txt_desc)
        tvTitle.text = "Karoline Bad "
        tvDesc.text = "When his parents are killed, billionaire playboy Bruce Wayne relocates to Asia, where he is mentored by Henri Ducard and Ra's Al Ghul in how to fight evil. When learning about the plan to wipe out evil in Gotham City by Ducard, Bruce prevents this plan from getting any further and heads back to his home."
        tvCast.text = "Christian Bale, Michael Caine, Liam Neeson as Henri Ducard/Ra's, Katie Holmes, Gary Oldman, Cillian Murph, Tom Wilkinson, Rutger Hauer, Ken Watanabe, Mark Boone Junior, Linus Roache, Morgan Freeman, Larry Holden, Colin McFarlane, Sara Stewart, Richard Brake, Tim Booth, John Nolan"

        recyclerView = findViewById(R.id.rv_similar)
        val adapter = MovieAdapter(movies, R.layout.movie_item_similar) { id ->
            startActivity(Intent(this@MovieActivity, MovieActivity::class.java))
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this@MovieActivity, 3)


        val toolBar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //TODOS os despositivos funcionem e ele busque corretamente apenas getDrawable tb dava
        val layerDrawable: LayerDrawable =
            ContextCompat.getDrawable(this@MovieActivity, R.drawable.shadows) as LayerDrawable

        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)

        val coverImg: ImageView = findViewById(R.id.image_background)
        coverImg.setImageDrawable(layerDrawable)

    }


}