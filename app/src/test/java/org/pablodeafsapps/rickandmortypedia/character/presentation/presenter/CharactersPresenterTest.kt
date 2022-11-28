package org.pablodeafsapps.rickandmortypedia.character.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.pablodeafsapps.rickandmortypedia.character.domain.CharactersDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.character.presentation.CharactersContract

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersPresenterTest {

    // Subject Under org.pablodeafsapps.rickandmortypedia.Test
    private lateinit var sut: CharactersPresenter
    private lateinit var testScheduler: TestCoroutineScheduler

    @Before
    fun setUp() {
        sut = CharactersPresenter(
            charactersView = Mockito.mock(CharactersContract.View::class.java),
            getAllCharactersUc = Mockito.mock(CharactersDomainLayerContract.PresentationLayer.UseCase::class.java)
        )
        testScheduler = TestCoroutineScheduler()
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When view gets created and the usecase response is successful, 'loadCharacters' is invoked`() = runTest {
        // given
        Mockito.`when`(sut.getAllCharactersUc.getAllCharacters()).thenReturn(getDummyCharactersEmptyResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        Mockito.verify(sut.charactersView).loadCharacters(getDummyCharactersEmpty())
    }

    @Test
    fun `When view gets created and the usecase response fails, 'showMessage' is invoked`() = runTest {
        // given
        Mockito.`when`(sut.getAllCharactersUc.getAllCharacters()).thenReturn(getDummyFailureResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        Mockito.verify(sut.charactersView).showMessage(Mockito.anyString())
    }

    private fun getDummyCharactersEmptyResult() : Result<Characters> =
        Result.success(getDummyCharactersEmpty())

    private fun getDummyCharactersEmpty(): Characters =
        Characters(results = emptyList())

    private fun getDummyFailureResult(): Result<Nothing> =
        Result.failure(Throwable())

}