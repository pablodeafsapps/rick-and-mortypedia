package org.pablodeafsapps.rickandmortypedia.login.di

import dagger.Module
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.login.data.di.LoginDataModule
import org.pablodeafsapps.rickandmortypedia.login.domain.di.LoginDomainModule
import org.pablodeafsapps.rickandmortypedia.login.presentation.view.LoginActivity

interface LoginComponentProvider {
    fun providesLoginComponent(): LoginComponent
}

@Module(subcomponents = [ LoginComponent::class ])
object LoginModule

@Subcomponent(modules = [
    LoginDomainModule::class, LoginDataModule::class
])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: LoginActivity)

}
