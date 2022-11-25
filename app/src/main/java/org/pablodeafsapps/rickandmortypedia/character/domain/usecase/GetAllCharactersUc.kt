package org.pablodeafsapps.rickandmortypedia.character.domain.usecase

import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import javax.inject.Inject

class GetAllCharactersUc @Inject constructor(
    private val characterRepository: DomainLayerContract.DataLayer.CharacterRepository
) : DomainLayerContract.PresentationLayer.UseCase {

    override suspend fun getAllCharacters(): Result<Characters> = characterRepository.getAllCharactersList()

}
