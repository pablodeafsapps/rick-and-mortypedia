package org.pablodeafsapps.rickandmortypedia.episode.presentation.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodesViewModelTest {

    private lateinit var sut: EpisodesViewModel
    private lateinit var testScheduler: TestCoroutineScheduler

    @Before
    fun setUp() {
        sut = EpisodesViewModel(
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
    fun `When view gets created and the usecase response is successful, 'episodes' property is not 'null'`() = runTest {
        // given
        Mockito.`when`(sut.getAllEpisodesUc.getAllEpisodes()).thenReturn(getDummyEpisodesEmptyResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        Assert.assertTrue(sut.episodes.value != null)
    }

    @Test
    fun `When view gets created and the usecase response fails, 'episodes' property remains 'null'`() = runTest {
        // given
        Mockito.`when`(sut.getAllEpisodesUc.getAllEpisodes()).thenReturn(getDummyFailureResult())
        // when
        sut.onViewCreated()
        advanceUntilIdle()
        // then
        Assert.assertTrue(sut.episodes.value == null)
    }

    private fun getDummyEpisodesEmptyResult() : Result<Episodes> =
        Result.success(getDummyEpisodesEmpty())

    private fun getDummyEpisodesEmpty(): Episodes =
        Episodes(results = emptyList())

    private fun getDummyFailureResult(): Result<Nothing> =
        Result.failure(Throwable())

}