package com.example.login

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class Testy {
    @Test
    fun testElementsContainerDisplayed() {
        ActivityScenario.launch(MainActivity::class.java).use {
            Espresso.onView(withId(R.id.username)).check(matches(ViewMatchers.isDisplayed()))
            Espresso.onView(withId(R.id.password)).check(matches(ViewMatchers.isDisplayed()))
            Espresso.onView(withId(R.id.login)).check(matches(ViewMatchers.isDisplayed()))
            Espresso.onView(withId(R.id.login)).check(matches(ViewMatchers.withText("Login")))
            Espresso.onView(withId(R.id.loading)).check(matches(ViewMatchers.isDisplayed()))

        }
    }
}