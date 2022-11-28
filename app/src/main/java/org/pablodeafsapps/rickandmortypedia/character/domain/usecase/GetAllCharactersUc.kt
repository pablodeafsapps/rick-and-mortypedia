package org.pablodeafsapps.rickandmortypedia.character.domain.usecase

import org.pablodeafsapps.rickandmortypedia.character.domain.CharactersDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import javax.inject.Inject

class GetAllCharactersUc @Inject constructor(
    private val characterRepository: CharactersDomainLayerContract.DataLayer.CharacterRepository
) : CharactersDomainLayerContract.PresentationLayer.UseCase {

    override suspend fun getAllCharacters(): Result<Characters> = characterRepository.getAllCharactersList()

}
