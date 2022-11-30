package org.pablodeafsapps.rickandmortypedia.login.domain.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.login.domain.LoginDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.login.domain.model.LoginUser
import org.pablodeafsapps.rickandmortypedia.login.domain.usecase.LoginUserWithEmailAndKeypassUc

@Module
object LoginDomainModule {

    @Provides
    fun providesLoginUserWithEmailAndKeypassUc(
        usecase: LoginUserWithEmailAndKeypassUc
    ): @JvmSuppressWildcards LoginDomainLayerContract.PresentationLayer.UseCase<LoginUser> = usecase

}
