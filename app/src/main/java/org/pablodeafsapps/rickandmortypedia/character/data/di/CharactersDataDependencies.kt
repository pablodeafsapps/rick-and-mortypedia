package org.pablodeafsapps.rickandmortypedia.character.data.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.CharactersDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.RickAndMortyCharacterDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.repository.RickAndMortyCharacterRepository
import org.pablodeafsapps.rickandmortypedia.character.data.utils.getRetrofitInstance
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CharactersDataModule {

    @Provides
    fun providesChracterRepository(charactersDataSource: CharactersDataSource) : DomainLayerContract.DataLayer.CharacterRepository =
        RickAndMortyCharacterRepository.apply {
            this.charactersDataSource = charactersDataSource
        }

    @Provides
    fun providesCharactersDataSource(rickAndMortyDataSource: RickAndMortyCharacterDataSource) : CharactersDataSource =
        rickAndMortyDataSource

    @Provides
    fun providesRetrofitInstance(converterFactory: Converter.Factory) : Retrofit =
        getRetrofitInstance(converterFactory = converterFactory)

    @Provides
    fun providesConverterFactory() : Converter.Factory = GsonConverterFactory.create()

}
