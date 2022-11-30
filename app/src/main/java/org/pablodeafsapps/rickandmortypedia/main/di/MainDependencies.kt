package org.pablodeafsapps.rickandmortypedia.main.di

import dagger.Module
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.main.presentation.di.MainPresentationModule
import org.pablodeafsapps.rickandmortypedia.main.presentation.view.MainActivity

interface MainComponentFactoryProvider {
    fun provideMainComponentFactory() : MainComponent.Factory
}

@Module(subcomponents = [ MainComponent::class ])
object MainModule

@Subcomponent(modules = [ MainPresentationModule::class ])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            presentationModule: MainPresentationModule
        ): MainComponent
    }

    fun inject(activity: MainActivity)

}
