package com.github.derleymad.youflix.utils

import com.github.derleymad.youflix.model.Category


interface MainCallBack {
    fun onPreExecute()
    fun onResult(categories: List<Category>)
    fun onFailure(message: String)
}
