package org.pablodeafsapps.rickandmortypedia

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.AdditionalMatchers.not

class MainActivityTest {

    private lateinit var activityScenario : ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun whenButtonIsClicked_aToastIsDisplayed() {
        onView(ViewMatchers.withId(R.id.button))
            .perform(ViewActions.click())
    }

}
