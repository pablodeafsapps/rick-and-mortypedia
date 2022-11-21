package org.pablodeafsapps.rickandmortypedia.character.domain.usecase

import org.pablodeafsapps.rickandmortypedia.character.data.repository.RickAndMortyCharacterRepository
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters

class GetAllCharactersUc : DomainLayerContract.PresentationLayer.UseCase {

    private val characterRepository: DomainLayerContract.DataLayer.CharacterRepository
            by lazy { RickAndMortyCharacterRepository }

    override suspend fun getAllCharacters(): Characters = characterRepository.getAllCharactersList()

}
