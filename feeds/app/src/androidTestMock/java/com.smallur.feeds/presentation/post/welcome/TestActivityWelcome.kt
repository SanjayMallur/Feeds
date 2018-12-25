package com.smallur.feeds.presentation.post.welcome


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.junit.runner.RunWith
import android.support.test.runner.AndroidJUnit4
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import com.smallur.feeds.R
import com.smallur.feeds.presentation.views.welcome.ActivityWelcome
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

/**
 * Test cases for [ActivityWelcome]
 * */
@RunWith(AndroidJUnit4::class)
@LargeTest
class TestActivityWelcome {
    @get:Rule
    val mActivityWelcomeTestRule = ActivityTestRule(ActivityWelcome::class.java)

    @Test
    fun testClickOnLogin(){
        //Check button is displayed to navigate to list of countries screen and click on it
        onView(allOf(withId(R.id.appCompatButton_login), isDisplayed()))
        onView(withId(R.id.appCompatButton_login)).perform(click())
    }
}