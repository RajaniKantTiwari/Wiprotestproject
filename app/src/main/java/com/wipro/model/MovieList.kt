package com.wipro.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieList(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("total") val total: String,
    @SerializedName("tv_shows") val tvShows: List<TvShow>
):  Parcelable