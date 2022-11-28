package org.pablodeafsapps.rickandmortypedia.episode.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.pablodeafsapps.rickandmortypedia.common.data.model.InfoDto
import org.pablodeafsapps.rickandmortypedia.episode.data.model.EpisodeDto
import org.pablodeafsapps.rickandmortypedia.episode.data.model.EpisodesDto
import org.pablodeafsapps.rickandmortypedia.episode.datasource.EpisodesDataSource
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes

private const val DEFAULT_INT_VALUE = 0
private const val DEFAULT_STRING_VALUE = "_"

@OptIn(ExperimentalCoroutinesApi::class)
class RickAndMortyEpisodeRepositoryTest {

    private lateinit var sut: RickAndMortyEpisodeRepository

    @Before
    fun setUp() {
        sut = RickAndMortyEpisodeRepository.apply {
            episodesRemoteDataSource = Mockito.mock(EpisodesDataSource.Remote::class.java)
            episodesLocalDataSource = Mockito.mock(EpisodesDataSource.Local::class.java)
        }
    }

    @Test
    fun `When all episodes are requested, and the API response is successful, a list of 'Episode' is returned`() = runTest {
        // given
        Mockito.`when`(sut.episodesRemoteDataSource.getAllEpisodesListResponse()).thenReturn(getDummyEpisodesDtoResult())
        // when
        val result: Result<Episodes> = sut.getAllEpisodesList()
        // then
        // state test
        Assert.assertTrue(result.isSuccess && result.getOrNull()?.results?.isNotEmpty() == true)
        // behaviour test
        Mockito.verify(sut.episodesLocalDataSource).saveEpisodeList(list = Mockito.anyList())
    }

    @Test
    fun `When all episodes are requested, and the API response is successful but 'null', the database is queried`() = runTest {
        // given
        Mockito.`when`(sut.episodesRemoteDataSource.getAllEpisodesListResponse()).thenReturn(getDummyEpisodesDtoNullResult())
        Mockito.`when`(sut.episodesLocalDataSource.fetchEpisodeList())
            .thenReturn(Mockito.anyList())
        // when
        sut.getAllEpisodesList()
        // then
        Mockito.verify(sut.episodesLocalDataSource).fetchEpisodeList()
    }

    @Test
    fun `When all episodes are requested, and the API response triggers an 'Exception', the database is queried`() = runTest {
        // given
        Mockito.`when`(sut.episodesRemoteDataSource.getAllEpisodesListResponse())
            .thenThrow(MockitoException::class.java)
        Mockito.`when`(sut.episodesLocalDataSource.fetchEpisodeList())
            .thenReturn(Mockito.anyList())
        // when
        sut.getAllEpisodesList()
        // then
        Mockito.verify(sut.episodesLocalDataSource).fetchEpisodeList()
    }

    private fun getDummyEpisodesDtoNullResult(): Result<EpisodesDto?> =
        Result.success(null)

    private fun getDummyEpisodesDtoResult(): Result<EpisodesDto?> =
        Result.success(getDummyEpisodesDto())

    private fun getDummyEpisodesDto(): EpisodesDto =
        EpisodesDto(
            info = getDummyInfoDto(),
            results = getDummyEpisodeDtoList()
        )

    private fun getDummyEpisodeDtoList(): List<EpisodeDto> = listOf(getDummyEpisodeDto())

    private fun getDummyEpisodeDto(): EpisodeDto =
        EpisodeDto(
            id = DEFAULT_INT_VALUE,
            name = DEFAULT_STRING_VALUE,
            airDate = DEFAULT_STRING_VALUE,
            episode = DEFAULT_STRING_VALUE,
            characters = emptyList(),
            url = DEFAULT_STRING_VALUE,
            created = DEFAULT_STRING_VALUE
        )

    private fun getDummyInfoDto(): InfoDto =
        InfoDto(
            count = DEFAULT_INT_VALUE,
            pages = DEFAULT_INT_VALUE,
            next = DEFAULT_STRING_VALUE,
            prev = DEFAULT_STRING_VALUE
        )

}
