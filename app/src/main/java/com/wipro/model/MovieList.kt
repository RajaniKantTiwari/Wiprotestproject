package com.wipro.model

data class MovieList(
    val page: Int,
    val pages: Int,
    val total: String,
    val tv_shows: List<TvShow>
)