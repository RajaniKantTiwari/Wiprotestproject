package com.wipro.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    @SerializedName("country") val country: String,
    @SerializedName("end_date") val endDate: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image_thumbnail_path") val imageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("network") val network: String,
    @SerializedName("permalink") val permalink: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("status") val status: String
) : Parcelable