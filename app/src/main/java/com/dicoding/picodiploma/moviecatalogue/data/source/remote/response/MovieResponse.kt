package com.dicoding.picodiploma.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    var id: String,
    var title: String,
    var release: String,
    var genre: String,
    var duration: String,
    var overview: String,
    var poster: String,
) : Parcelable
