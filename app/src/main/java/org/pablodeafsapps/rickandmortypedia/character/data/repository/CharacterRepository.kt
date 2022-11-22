package org.pablodeafsapps.rickandmortypedia.character.data.repository

import org.pablodeafsapps.rickandmortypedia.character.data.datasource.CharactersDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.RickAndMortyCharacterDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.utils.toCharacters
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Character
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters

object RickAndMortyCharacterRepository: DomainLayerContract.DataLayer.CharacterRepository {

    private val charactersDataSource: CharactersDataSource by lazy { RickAndMortyCharacterDataSource() }

    override suspend fun getAllCharactersList(): Characters =
        charactersDataSource.getAllCharactersListResponse().toCharacters()

    override suspend fun getAllCharactersListByPage(page: Int): Characters {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: Int): Character {
        TODO("Not yet implemented")
    }

    override suspend fun getMultipleCharactersById(ids: List<Int>): Characters {
        TODO("Not yet implemented")
    }

}
