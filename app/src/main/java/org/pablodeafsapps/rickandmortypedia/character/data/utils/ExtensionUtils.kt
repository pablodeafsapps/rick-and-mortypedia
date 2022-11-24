package org.pablodeafsapps.rickandmortypedia.character.data.utils

import org.pablodeafsapps.rickandmortypedia.character.data.db.CharacterEntity
import org.pablodeafsapps.rickandmortypedia.character.data.db.LocationEntity
import org.pablodeafsapps.rickandmortypedia.character.data.db.OriginEntity
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharacterDto
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
import org.pablodeafsapps.rickandmortypedia.character.data.model.LocationDto
import org.pablodeafsapps.rickandmortypedia.character.data.model.OriginDto
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Character
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Location
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Origin

fun CharactersDto?.toCharacters() : Characters =
    Characters(results = this?.results?.toCharacterList() ?: emptyList())

private fun List<CharacterDto>.toCharacterList() : List<Character> =
    map { dto ->
        with(dto) {
            Character(
                id = id,
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                origin = origin.toBo(),
                location = location.toBo(),
                image = image,
                episode = episode,
                url = url,
                created = created
            )
        }
    }

private fun OriginDto.toBo() : Origin = Origin(name = name, url = url)

private fun LocationDto.toBo() : Location = Location(name = name, url = url)

fun List<CharacterDto>.toEntity() : List<CharacterEntity> =
    map { dto ->
        with(dto) {
            CharacterEntity(
                id = id,
                page = 1,
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                origin = OriginEntity(name = origin.name, url = origin.url),
                location = LocationEntity(name = location.name, url = location.url),
                image = image,
                episode = episode,
                url = url,
                created = created
            )
        }
}
