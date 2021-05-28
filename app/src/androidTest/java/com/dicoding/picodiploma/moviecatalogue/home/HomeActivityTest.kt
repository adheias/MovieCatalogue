package com.dicoding.picodiploma.moviecatalogue.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvs = DataDummy.generateDummyTvShow()


    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvs() {
        onView(withText(R.string.tvs)).perform(click())
        onView(withId(R.id.rv_tvs)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvs)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvs.size))
    }

    @Test
    fun loadDetailTvs() {
        onView(withText(R.string.tvs)).perform(click())
        onView(withId(R.id.rv_tvs)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
    }

}