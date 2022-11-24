package org.pablodeafsapps.rickandmortypedia.character.data.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.CharactersDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.RickAndMortyCharacterDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.repository.RickAndMortyCharacterRepository
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract

@Module
class CharactersDataModule {

    @Provides
    fun providesCharacterRepository(
        remoteDataSource: CharactersDataSource.Remote,
        localDataSource: CharactersDataSource.Local
    ): DomainLayerContract.DataLayer.CharacterRepository = RickAndMortyCharacterRepository.apply {
        charactersRemoteDataSource = remoteDataSource
        charactersLocalDataSource = localDataSource
    }

    @Provides
    fun providesCharactersRemoteDataSource(rickAndMortyDataSource: RickAndMortyCharacterDataSource) : CharactersDataSource.Remote =
        rickAndMortyDataSource

    @Provides
    fun providesCharactersLocalDataSource(rickAndMortyDataSource: RickAndMortyCharacterDataSource) : CharactersDataSource.Local =
        rickAndMortyDataSource

}
