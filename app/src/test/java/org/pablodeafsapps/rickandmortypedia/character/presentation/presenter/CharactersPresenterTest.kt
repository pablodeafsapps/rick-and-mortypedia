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
import org.pablodeafsapps.rickandmortypedia.utils.mock

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersPresenterTest {

    // Subject Under org.pablodeafsapps.rickandmortypedia.Test
    private lateinit var sut: CharactersPresenter
    private lateinit var mockCharactersView: CharactersContract.View
    private lateinit var mockGetAllCharactersUc: CharactersDomainLayerContract.PresentationLayer.UseCase<Characters>
    private lateinit var mockGetCharactersNextPageUc: CharactersDomainLayerContract.PresentationLayer.UseCase<Characters>
    private lateinit var testScheduler: TestCoroutineScheduler

    @Before
    fun setUp() {
        mockCharactersView = Mockito.mock(CharactersContract.View::class.java)
        mockGetAllCharactersUc = mock()
        mockGetCharactersNextPageUc = mock()
        sut = CharactersPresenter(
            charactersView = mockCharactersView,
            getAllCharactersUc = mockGetAllCharactersUc,
            getCharactersNextPageUc = mockGetCharactersNextPageUc
        )
        testScheduler = TestCoroutineScheduler()
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When view gets created and the usecase response is successful, 'loadCharacters' is invoked`() = runTest {
        // given
        Mockito.`when`(mockGetAllCharactersUc()).thenReturn(getDummyCharactersEmptyResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        Mockito.verify(mockCharactersView).loadCharacters(getDummyCharactersEmpty())
    }

    @Test
    fun `When view gets created and the usecase response fails, 'showMessage' is invoked`() = runTest {
        // given
        Mockito.`when`(mockGetAllCharactersUc()).thenReturn(getDummyFailureResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        Mockito.verify(mockCharactersView).showMessage(Mockito.anyString())
    }

    @Test
    fun `When the end of the scroll is reached and the usecase response is successful, 'loadCharacters' is invoked`() = runTest {
        // given
        Mockito.`when`(mockGetCharactersNextPageUc()).thenReturn(getDummyCharactersEmptyResult())
        // when
        sut.onEndOfScrollReached()
        advanceUntilIdle()
        // then
        Mockito.verify(mockCharactersView).loadCharacters(getDummyCharactersEmpty())
    }

    @Test
    fun `When the end of the scroll is reached and the usecase response fails, 'loadCharacters' is not invoked`() = runTest {
        // given
        Mockito.`when`(mockGetCharactersNextPageUc()).thenReturn(getDummyFailureResult())
        // when
        sut.onEndOfScrollReached()
        advanceUntilIdle()
        // then
        Mockito.verify(mockCharactersView, Mockito.never()).loadCharacters(getDummyCharactersEmpty())
    }

    private fun getDummyCharactersEmptyResult() : Result<Characters> =
        Result.success(getDummyCharactersEmpty())

    private fun getDummyCharactersEmpty(): Characters =
        Characters(results = emptyList())

    private fun getDummyFailureResult(): Result<Nothing> =
        Result.failure(Throwable())

}
