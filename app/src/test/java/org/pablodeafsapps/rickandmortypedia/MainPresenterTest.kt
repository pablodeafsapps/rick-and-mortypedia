package org.pablodeafsapps.rickandmortypedia

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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
    @Test
    fun `When 'click me' option is selected and 'num' greater than 0_5, 'showMessage' is invoke`() {
        // given
        val num: Double = 0.6
        // when
        sut.onClickmeOptionSelected(num = num)
        // then
        verify(sut.mainView).showMessage()
    }
    @Test
    fun `When 'click me' option is selected and 'num' less than or equal 0_5 'showLogMessage' is invoke`() {
        // given
        val num: Double = 0.4
        // when
        sut.onClickmeOptionSelected(num = num)
        // then
        verify(sut.mainView).showLogMessage()
    }
    @Test
    fun `When 'click me' option is selected, 'greetings' is assigned 'Hello'`() {
        // when
        sut.onClickmeOptionSelected(3.0)
        // then
        assert(sut.greetings == "Hello")
    }

    @Test
    fun `When 'Launch request' option is selected, 'greetings' is updated`() = runTest {
        // given
        `when`(sut.getAllCharactersUc.getAllCharacters()).thenReturn(getDummyCharactersEmptyResult())
        // when
        sut.onLaunchRequestOptionSelected()
        // then
        assert(sut.greetings != null)
    }

    private fun getDummyCharactersEmptyResult() : Result<Characters> =
        Result.success(getDummyCharactersEmpty())

    private fun getDummyCharactersEmpty(): Characters =
        Characters(
            results = emptyList()
        )

}
