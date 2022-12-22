package com.github.derleymad.youflix.utils

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.github.derleymad.youflix.R
import com.github.derleymad.youflix.model.Movies
import com.github.derleymad.youflix.ui.MainActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException
import java.util.concurrent.Executors

class GetMovies(private val callback: MainActivity){

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()
    lateinit var responseCode : String

    interface Callback{
        fun onPreExecuteQuery()
        fun onResultQuery(movies: List<Movies>)
        fun onFailureQuery(message: String)
    }

    fun execute(){
        val urlQuery = "https://www.tvmap.com.br/Programacao/21-dez-2022/18.00hs"
//        val urlQuery= "https://www.tvmap.com.br/Programacao/"

        callback.onPreExecuteQuery()
        executor.execute{
            val movies = mutableListOf<Movies>()

            try{
                val page = Jsoup
                    .connect(urlQuery)
                    .get()

                val body = page.body()
                val images = body.select("#watchingGrid #zc-grid table tbody tr td div a img").eachAttr("src")
//                val table = body.select("#watchingGrid #zc-grid table .zc-pg").eachText()
                val table = body.select(".innerTable tbody tr")
                val moviesDesc = mutableListOf<String>()
                for(i in table){
                    moviesDesc.add(i.text())
                }
                moviesDesc.forEach { Log.i("testeMovie", it.toString()) }

                var j : Int = 0
                for(i in images){
                    movies.add(
                        Movies(
                            moviesDesc[j],
                            "12:00",
                            i.toString()
                        )
                    )
                    j += 1
                }
                Log.i("testMovie",images.toString())
                handler.post{callback.onResultQuery(movies)}

            }catch (e: IOException){
                val message = e.message ?: callback.getString(R.string.uknown_error)
                handler.post { callback.onFailureQuery(message)  }
            }
        }
        //FIM DO EXECUTORS
    }
}