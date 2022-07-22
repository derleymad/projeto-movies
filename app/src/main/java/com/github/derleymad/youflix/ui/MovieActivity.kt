package com.github.derleymad.youflix.ui

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.derleymad.youflix.R
import com.github.derleymad.youflix.adapters.MovieAdapter
import com.github.derleymad.youflix.model.Movie
import com.github.derleymad.youflix.model.MovieDetail
import com.github.derleymad.youflix.utils.MovieCallBack
import com.github.derleymad.youflix.utils.MovieTask
import com.squareup.picasso.Picasso

class MovieActivity : AppCompatActivity(), MovieTask.Callback {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var movieDetail: MovieDetail
    private lateinit var imagePlay: ImageView
    private lateinit var adapter: MovieAdapter
    private lateinit var tvTitle: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvCast: TextView
    private lateinit var coverImg: ImageView
    private var id:Int = 0
    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        progressBar = findViewById(R.id.progressBarMovieDetail)
        imagePlay = findViewById(R.id.imagePlay)
        tvTitle = findViewById(R.id.movie_txt_title)
        tvCast = findViewById(R.id.movie_txt_cast)
        tvDesc = findViewById(R.id.movie_txt_desc)

        recyclerView = findViewById(R.id.rv_similar)
        adapter = MovieAdapter(movies, R.layout.movie_item_similar){
            Toast.makeText(this@MovieActivity,"Você ainda não pode clicar aqui! :(",Toast.LENGTH_SHORT).show()
        }

        id = intent?.extras?.getInt("id",0) ?: throw IllegalStateException("Id not found")

//        movies.add(Movie(id,"asd")


//        tvTitle.text = movieDetail.title
//        tvDesc.text =  movieDetail.desc
//        tvCast.text =  movieDetail.cast
//
//        movies.addAll(movieDetail.similarMovies)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this@MovieActivity, 3)

//        MovieRequest(this@MovieActivity).execute("https://derleymad.github.io/youflix/movie/$id.json")

        val toolBar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        val layerDrawable: LayerDrawable =
            ContextCompat.getDrawable(this@MovieActivity, R.drawable.shadows) as LayerDrawable

//        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)
//        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)
//        coverImg = findViewById(R.id.image_background)
//        coverImg.setImageDrawable(layerDrawable)

        MovieTask(this@MovieActivity).execute("https://derleymad.github.io/youflix/movie/$id.json")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPreExecute() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onResult(movieDetail: MovieDetail) {
        progressBar.visibility = View.GONE
        tvTitle.text = movieDetail.movie.title
        tvDesc.text = movieDetail.movie.desc
        tvCast.text = movieDetail.movie.cast
        movies.clear()
        movies.addAll(movieDetail.similars)
        adapter.notifyDataSetChanged()

//        val layerDrawable: LayerDrawable = ContextCompat.getDrawable(this@MovieActivity, R.drawable.shadows) as LayerDrawable
        val coverImg: ImageView = findViewById(R.id.image_background)
//        coverImg.setImageDrawable(layerDrawable)

        Picasso.get()
            .load(movieDetail.movie.coverUrl)
            .placeholder(R.drawable.shadows)
            .error(R.drawable.shadows)
            .into(coverImg)

        imagePlay.setOnClickListener {
            Toast.makeText(this@MovieActivity,"${movieDetail.movie.title} estará disponível em breve!",Toast.LENGTH_LONG).show()
        }
    }

    override fun onFailure(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this@MovieActivity,message,Toast.LENGTH_SHORT)
    }


}