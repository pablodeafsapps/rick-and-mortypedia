package org.pablodeafsapps.rickandmortypedia.character.data.utils

import org.pablodeafsapps.rickandmortypedia.character.data.db.CharacterEntity
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharacterDto
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Character
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.common.utils.toBo
import org.pablodeafsapps.rickandmortypedia.common.utils.toEntity

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

fun CharactersDto.toCharactersEntity(): List<CharacterEntity> = results.map { dto ->
    with(dto) {
        CharacterEntity(
            id = id,
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender,
            origin = origin.toEntity(),
            location = location.toEntity(),
            image = image,
            episode = episode,
            url = url,
            created = created
        )
    }
}

fun List<CharacterEntity>.toCharacters(): Characters =
    Characters(
        results = map { entity ->
            with(entity) {
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
    )
