package org.pablodeafsapps.rickandmortypedia.common.data.model

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

data class OriginDto(
    val name: String,
    val url: String
)

data class LocationDto(
    val name: String,
    val url: String
)
