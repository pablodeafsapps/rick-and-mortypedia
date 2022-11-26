package org.pablodeafsapps.rickandmortypedia

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters

class MainPresenterTest {

    // Subject Under org.pablodeafsapps.rickandmortypedia.Test
    private lateinit var sut: MainPresenter
    private lateinit var testScheduler: TestCoroutineScheduler

    @Before
    fun setUp() {
        sut = MainPresenter(
            mainView = mock(Mvp.View::class.java),
            getAllCharactersUc = mock(DomainLayerContract.PresentationLayer.UseCase::class.java)
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
        `when`(sut.getAllCharactersUc.getAllCharacters()).thenReturn(getDummyCharactersEmptyResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        verify(sut.mainView).loadCharacters(getDummyCharactersEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When view gets created and the usecase response fails, 'showMessage' is invoked`() = runTest {
        // given
        `when`(sut.getAllCharactersUc.getAllCharacters()).thenReturn(getDummyFailureResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        verify(sut.mainView).showMessage(anyString())
    }

    private fun getDummyCharactersEmptyResult() : Result<Characters> =
        Result.success(getDummyCharactersEmpty())

    private fun getDummyCharactersEmpty(): Characters =
        Characters(results = emptyList())

    private fun getDummyFailureResult(): Result<Nothing> =
        Result.failure(Throwable())

}
