package org.pablodeafsapps.rickandmortypedia

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MainPresenterTest {

    // Subject Under org.pablodeafsapps.rickandmortypedia.Test
    private lateinit var sut: MainPresenter

    @Before
    fun setUp() {
        sut = MainPresenter(mock(Mvp.View::class.java))
    }

    @After
    fun tearDown() {
    }
    @Test
    fun `When 'click me' option is selected and 'num' greater than 0_5, 'showMessage' is invoke`() {
        // given
        val num: Double = 0.6
        //when
        sut.onClickmeOptionSelected(num = num)
        //assert
        verify(sut.mainView).showMessage()
    }
    @Test
    fun `When 'click me' option is selected and 'num' less than or equal 0_5 'showLogMessage' is invoke`() {
        // given
        val num: Double = 0.4
        //when
        sut.onClickmeOptionSelected(num = num)
        //assert
        verify(sut.mainView).showLogMessage()
    }
    @Test
    fun `When 'click me' option is selected, 'greetings' is assigned 'Hello'`() {
        //when
        sut.onClickmeOptionSelected(3.0)
        //assert
        assert(sut.greetings == "Hello")
    }

}