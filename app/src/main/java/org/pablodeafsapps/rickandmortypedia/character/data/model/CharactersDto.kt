package org.pablodeafsapps.rickandmortypedia.character.data.model

import org.pablodeafsapps.rickandmortypedia.common.data.model.InfoDto
import org.pablodeafsapps.rickandmortypedia.common.data.model.LocationDto
import org.pablodeafsapps.rickandmortypedia.common.data.model.OriginDto

data class MultipleCharactersDto(
    val characters: List<CharacterDto>
)

data class CharactersDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)

data class CharacterDto(
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
