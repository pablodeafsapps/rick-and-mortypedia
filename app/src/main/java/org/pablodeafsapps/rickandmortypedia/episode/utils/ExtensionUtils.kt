package org.pablodeafsapps.rickandmortypedia.episode.utils

import org.pablodeafsapps.rickandmortypedia.episode.data.db.EpisodeEntity
import org.pablodeafsapps.rickandmortypedia.episode.data.model.EpisodeDto
import org.pablodeafsapps.rickandmortypedia.episode.data.model.EpisodesDto
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episode
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes

fun EpisodesDto?.toEpisodes() : Episodes =
    Episodes(results = this?.results?.toEpisodeList() ?: emptyList())

private fun List<EpisodeDto>.toEpisodeList() : List<Episode> =
    map { dto ->
        with(dto) {
            Episode(
                id = id,
                name = name,
                airDate = airDate,
                episode = episode,
                characters = characters,
                url = url,
                created = created
            )
        }
    }

fun EpisodesDto.toEpisodesEntity(): List<EpisodeEntity> = results.map { dto ->
    with(dto) {
        EpisodeEntity(
            id = id,
            name = name,
            airDate = airDate,
            episode = episode,
            characters = characters,
            url = url,
            created = created
        )
    }
}

fun List<EpisodeEntity>.toEpisodes(): Episodes =
    Episodes(
        results = map { entity ->
            with(entity) {
                Episode(
                    id = id,
                    name = name,
                    airDate = airDate,
                    episode = episode,
                    characters = characters,
                    url = url,
                    created = created
                )
            }
        }
    )
