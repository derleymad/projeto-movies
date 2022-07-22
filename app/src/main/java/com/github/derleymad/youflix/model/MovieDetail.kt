package com.github.derleymad.youflix.model

data class MovieDetail(
    val movie: Movie,
    val similars: List<Movie>
)
