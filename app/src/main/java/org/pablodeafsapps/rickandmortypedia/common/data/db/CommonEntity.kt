package org.pablodeafsapps.rickandmortypedia.common.data.model.db

import androidx.room.ColumnInfo


data class OriginEntity(
    @ColumnInfo(name = "origin_name") val name: String,
    @ColumnInfo(name = "origin_url") val url: String
)

data class LocationEntity(
    @ColumnInfo(name = "location_name") val name: String,
    @ColumnInfo(name = "location_url") val url: String
)
