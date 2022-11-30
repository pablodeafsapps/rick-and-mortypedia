package org.pablodeafsapps.rickandmortypedia.character.domain.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.domain.CharactersDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.character.domain.usecase.GetAllCharactersUc
import org.pablodeafsapps.rickandmortypedia.character.domain.usecase.GetCharactersNextPageUc
import javax.inject.Named

@Module
class CharactersDomainModule {

    @Provides
    @Named("get_all_characters")
    fun providesGetAllCharactersUc(usecase: GetAllCharactersUc) : @JvmSuppressWildcards CharactersDomainLayerContract.PresentationLayer.UseCase<Characters> = usecase

    @Provides
    @Named("get_characters_next_page")
    fun providesGetCharactersNextPageUc(usecase: GetCharactersNextPageUc) : @JvmSuppressWildcards CharactersDomainLayerContract.PresentationLayer.UseCase<Characters> = usecase

}
