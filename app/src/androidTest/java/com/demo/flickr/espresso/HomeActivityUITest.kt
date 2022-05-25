package com.demo.flickr.espresso

import android.graphics.Color
import android.support.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.demo.flickr.R
import com.demo.flickr.ui.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.mockito.AdditionalMatchers.not


class HomeActivityUITest {

    @Rule
    var mActivityRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(HomeActivity::class.java)

    @Test
    fun ensureColorChangesWork() {
        onView(withId(R.id.btnShowPhotos)).perform(click())
        onView(withId(R.id.btnShowPhotos)).check(matches(hasTextColor(Color.GRAY)))
    }

    @Test
    fun buttonIsEnabled() {
        onView(withId(R.id.btnShowPhotos)).check(matches(isEnabled()))
    }

    @Test
    fun buttonIsDisplayed() {
        onView(withId(R.id.btnShowPhotos)).check(matches(isDisplayed()))
    }

    @Test
    fun buttonIsCompletelyDisplayed() {
        onView(withId(R.id.btnShowPhotos)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun buttonIsNotSelectable() {
        onView(withId(R.id.btnShowPhotos)).check(matches(not(isSelected())))
    }

    @Test
    fun buttonIsClickable() {
        onView(withId(R.id.btnShowPhotos)).check(matches(not(isClickable())))
    }

    @Test
    fun buttonWithText() {
        onView(withId(R.id.btnShowPhotos)).check(matches(withText(R.string.button_text)))
    }
}