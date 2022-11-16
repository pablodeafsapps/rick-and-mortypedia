package org.pablodeafsapps.rickandmortypedia.data.model

data class MultipleCharactersDto(
    val characters: List<ResultDto>
)

data class CharactersDto(
    val info: InfoDto,
    val results: List<ResultDto>
)

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

data class ResultDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDto,
    val location: LocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class OriginDto(
    val name: String,
    val url: String
)

data class LocationDto(
    val name: String,
    val url: String
)
