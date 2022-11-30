package org.pablodeafsapps.rickandmortypedia.character.domain.usecase

import org.pablodeafsapps.rickandmortypedia.character.domain.CharactersDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import javax.inject.Inject

class GetCharactersNextPageUc @Inject constructor(
    private val characterRepository: CharactersDomainLayerContract.DataLayer.CharacterRepository
) : CharactersDomainLayerContract.PresentationLayer.UseCase<Characters> {

    override suspend fun invoke(): Result<Characters> = characterRepository.getCharactersNextPage()

}
