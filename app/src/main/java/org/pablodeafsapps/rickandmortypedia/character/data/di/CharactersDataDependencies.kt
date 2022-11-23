package org.pablodeafsapps.rickandmortypedia.character.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.CharactersDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.RickAndMortyCharacterDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.repository.RickAndMortyCharacterRepository
import org.pablodeafsapps.rickandmortypedia.character.data.utils.getRetrofitInstance
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.common.db.ApplicationDatabase
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CharactersDataModule(private val applicationContext: Context) {

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

    @Provides
    fun providesRetrofitInstance(converterFactory: Converter.Factory) : Retrofit =
        getRetrofitInstance(converterFactory = converterFactory)

    @Provides
    fun providesConverterFactory() : Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun providesRoomDatabaseInstance(): ApplicationDatabase =
        Room.databaseBuilder(
            applicationContext,
            ApplicationDatabase::class.java, "rick-and-morty-db"
        ).build()

}
