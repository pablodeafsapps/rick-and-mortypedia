package org.pablodeafsapps.rickandmortypedia.login.data.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.login.data.datasource.FirebaseAuthDataSource
import org.pablodeafsapps.rickandmortypedia.login.data.datasource.LoginDataSource
import org.pablodeafsapps.rickandmortypedia.login.data.repository.RickAndMortyLoginRepository
import org.pablodeafsapps.rickandmortypedia.login.domain.LoginDomainLayerContract

@Module
object LoginDataModule {

    @Provides
    fun providesLoginRepository(
        dataSource: LoginDataSource
    ): LoginDomainLayerContract.DataLayer.LoginRepository = RickAndMortyLoginRepository.apply {
        loginDataSource = dataSource
    }

    @Provides
    fun providesLoginDataSource(firebaseAuthDataSource: FirebaseAuthDataSource): LoginDataSource =
        firebaseAuthDataSource

    @Provides
    fun providesFirebaseAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()

}
