package org.pablodeafsapps.rickandmortypedia.episode.domain.model

data class Episodes(
    val results: List<Episode>
)

data class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
