package org.pablodeafsapps.rickandmortypedia.episode.presentation.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodesViewModelTest {

    private lateinit var sut: EpisodesViewModel
    private lateinit var getAllEpisodesUc: EpisodesDomainLayerContract.PresentationLayer.UseCase
    private lateinit var testScheduler: TestCoroutineScheduler

    @Before
    fun setUp() {
        getAllEpisodesUc = FakeGetAllEpisodesUc()
        testScheduler = TestCoroutineScheduler()
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When viewmodel is initialised and the usecase response is successful, 'episodes' gets a non-null value`() = runTest {
        // when
        sut = EpisodesViewModel(getAllEpisodesUc = getAllEpisodesUc)
        advanceUntilIdle()
        // then
        Assert.assertTrue(sut.episodes.value != null)
    }

}

class FakeGetAllEpisodesUc : EpisodesDomainLayerContract.PresentationLayer.UseCase {

    override suspend fun getAllEpisodes(): Result<Episodes> =
        Result.success(Episodes(results = emptyList()))

}
