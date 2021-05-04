package com.dicoding.picodiploma.moviecatalogue.data

data class MovieEntity(
    var id: String,
    var title: String,
    var realeaseDate: String,
    var genre: String,
    var duration: String,
    var overview: String,
    var poster: Int,
    var score: String,
)
