package org.pablodeafsapps.rickandmortypedia.common.utils

import org.pablodeafsapps.rickandmortypedia.character.domain.model.Location
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Origin
import org.pablodeafsapps.rickandmortypedia.common.data.model.LocationDto
import org.pablodeafsapps.rickandmortypedia.common.data.model.OriginDto
import org.pablodeafsapps.rickandmortypedia.common.db.LocationEntity
import org.pablodeafsapps.rickandmortypedia.common.db.OriginEntity

fun OriginDto.toBo() : Origin = Origin(name = name, url = url)

fun LocationDto.toBo() : Location = Location(name = name, url = url)

fun OriginDto.toEntity(): OriginEntity =
    OriginEntity(
        name = name,
        url = url
    )

fun LocationDto.toEntity(): LocationEntity =
    LocationEntity(
        name = name,
        url = url
    )

fun OriginEntity.toBo(): Origin =
    Origin(
        name = name,
        url = url
    )

fun LocationEntity.toBo(): Location =
    Location(
        name = name,
        url = url
    )
