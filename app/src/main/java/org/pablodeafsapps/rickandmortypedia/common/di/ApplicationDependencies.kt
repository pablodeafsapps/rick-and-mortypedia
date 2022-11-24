package org.pablodeafsapps.rickandmortypedia.common.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.data.utils.getRetrofitInstance
import org.pablodeafsapps.rickandmortypedia.common.db.ApplicationDatabase
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class UtilsModule(private val applicationContext: Context) {

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
