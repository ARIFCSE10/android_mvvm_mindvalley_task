package com.mba.mindvalley.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.mba.mindvalley.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)


//    @Before
//    fun setUp() {
//    }
//
//    @After
//    fun tearDown() {
//    }

    @Test
    fun isAppRunnable() {
        val openApp = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.pull_to_refresh_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun isPullToRefreshWorking() {
        val openApp = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.pull_to_refresh_layout)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(R.id.pull_to_refresh_layout)).perform(swipeDown())
        Thread.sleep(3000)
        onView(withId(R.id.new_episode_rv)).perform(swipeUp())
        Thread.sleep(3000)
    }

    @Test
    fun isAppClosesCorrectly() {
        with(mActivityTestRule) {
            finishActivity()
            launchActivity(null)
        }
    }

    @Test
    fun isAppReopensCorrectly() {
        for (i in 0..3) {
            with(mActivityTestRule) {
                Thread.sleep(1500)
                finishActivity()
                Thread.sleep(1500)
                launchActivity(null)
            }
        }
    }
}