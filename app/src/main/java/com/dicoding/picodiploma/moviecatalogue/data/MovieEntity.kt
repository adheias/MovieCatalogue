package com.dicoding.picodiploma.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var id: String,
    var title: String,
    var realease: String,
    var genre: String,
    var duration: String,
    var overview: String,
    var poster: String
) : Parcelable
