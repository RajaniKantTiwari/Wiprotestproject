package com.wipro.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TvShowEntity")
data class TvShowEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "end_date") val endDate: String?,
    @ColumnInfo(name = "image_thumbnail_path") val imageUrl: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "network") val network: String?,
    @ColumnInfo(name = "permalink") val permalink: String?,
    @ColumnInfo(name = "start_date") val startDate: String?,
    @ColumnInfo(name = "status") val status: String?
)