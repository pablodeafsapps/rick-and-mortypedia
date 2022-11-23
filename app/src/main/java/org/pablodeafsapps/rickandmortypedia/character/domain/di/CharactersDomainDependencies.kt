package org.pablodeafsapps.rickandmortypedia.character.domain.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.usecase.GetAllCharactersUc

@Module
class CharactersDomainModule {

    @Provides
    fun providesGetAllCharactersUc(usecase: GetAllCharactersUc) : @JvmSuppressWildcards DomainLayerContract.PresentationLayer.UseCase = usecase

}
