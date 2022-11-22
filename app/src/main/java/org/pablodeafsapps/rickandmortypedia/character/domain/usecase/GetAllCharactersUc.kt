package org.pablodeafsapps.rickandmortypedia.character.domain.usecase

import org.pablodeafsapps.rickandmortypedia.character.data.repository.RickAndMortyCharacterRepository
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import javax.inject.Inject

class GetAllCharactersUc @Inject constructor(
//    private val characterRepository: DomainLayerContract.DataLayer.CharacterRepository
) : DomainLayerContract.PresentationLayer.UseCase {

    private val characterRepository: DomainLayerContract.DataLayer.CharacterRepository
            by lazy { RickAndMortyCharacterRepository }

    override suspend fun getAllCharacters(): Characters = characterRepository.getAllCharactersList()

}
