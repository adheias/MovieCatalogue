package com.dicoding.picodiploma.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowResponse(
    var id: String,
    var title: String,
    var genre: String,
    var duration: String,
    var overview: String,
    var poster: String,
) : Parcelable
