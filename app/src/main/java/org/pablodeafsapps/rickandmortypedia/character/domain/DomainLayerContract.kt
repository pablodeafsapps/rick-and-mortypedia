package org.pablodeafsapps.rickandmortypedia.character.domain

import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Character

interface DomainLayerContract {

    interface PresentationLayer {

        interface UseCase {

            suspend fun getAllCharacters(): Result<Characters>

        }

    }

    interface DataLayer {

        interface CharacterRepository {

            suspend fun getAllCharactersList() : Result<Characters>

            suspend fun getAllCharactersListByPage(page: Int) : Characters

            suspend fun getCharacterById(id: Int) : Character

            suspend fun getMultipleCharactersById(ids: List<Int>) : Characters

        }

    }

}
