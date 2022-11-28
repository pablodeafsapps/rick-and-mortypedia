package org.pablodeafsapps.rickandmortypedia.episode.presentation.presenter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes
import org.pablodeafsapps.rickandmortypedia.episode.presentation.EpisodesContract

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodesPresenterTest {

    // Subject Under org.pablodeafsapps.rickandmortypedia.Test
    private lateinit var sut: EpisodesPresenter
    private lateinit var testScheduler: TestCoroutineScheduler

    @Before
    fun setUp() {
        sut = EpisodesPresenter(
            episodesView = Mockito.mock(EpisodesContract.View::class.java),
            getAllEpisodesUc = Mockito.mock(EpisodesDomainLayerContract.PresentationLayer.UseCase::class.java)
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
        Mockito.`when`(sut.getAllEpisodesUc.getAllEpisodes()).thenReturn(getDummyEpisodesEmptyResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        Mockito.verify(sut.episodesView).loadEpisodes(getDummyEpisodesEmpty())
    }

    @Test
    fun `When view gets created and the usecase response fails, 'showMessage' is invoked`() = runTest {
        // given
        Mockito.`when`(sut.getAllEpisodesUc.getAllEpisodes()).thenReturn(getDummyFailureResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        Mockito.verify(sut.episodesView).showMessage(Mockito.anyString())
    }

    private fun getDummyEpisodesEmptyResult() : Result<Episodes> =
        Result.success(getDummyEpisodesEmpty())

    private fun getDummyEpisodesEmpty(): Episodes =
        Episodes(results = emptyList())

    private fun getDummyFailureResult(): Result<Nothing> =
        Result.failure(Throwable())

}
