package com.dicoding.picodiploma.moviecatalogue.data

data class TvShowEntity(
    var id: String,
    var title: String,
    var genre: String,
    var duration: String,
    var overview: String,
    var poster: Int,
    var score: String,
) {
    var contentEntity: ContentEntity? = null
}
