package com.github.derleymad.youflix.utils

import com.github.derleymad.youflix.model.MovieDetail

interface MovieCallBack {
    fun onPreExecute()
    fun onResponse(movieDetail:MovieDetail)
    fun onFailure(message:String)
}