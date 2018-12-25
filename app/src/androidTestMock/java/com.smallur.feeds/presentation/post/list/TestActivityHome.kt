package com.smallur.feeds.presentation.post.list

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.smallur.feeds.R
import com.smallur.feeds.presentation.ActivityHome
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Test cases for [ActivityHome]
 * */
@RunWith(AndroidJUnit4::class)
@LargeTest
class TestActivityHome {

    @get:Rule
    val activityHomeTestRule = ActivityTestRule(ActivityHome::class.java)

    @Test
    fun testClickFirstItem(){
        //Check RecyclerView is displayed and click on first item
        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        click()))
    }

    @Test
    fun testClickSecondItem(){
        //Check RecyclerView is displayed and click on second item
        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                        click()))
    }
}