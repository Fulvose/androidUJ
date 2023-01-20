package com.example.shop

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection
import java.net.URL


@RunWith(AndroidJUnit4::class)
class Testy {
    @Test
    fun testElementsContainerDisplayed() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withId(R.id.nameView)).check(matches(isDisplayed()))
            onView(withId(R.id.priceView)).check(matches(isDisplayed()))
            onView(withId(R.id.nameView)).check(matches(withText("Product Name")))
            onView(withId(R.id.priceView)).check(matches(withText("100$")))
            onView(withId(R.id.button)).check(matches(withText("Add to cart")))
        }
    }

    @Test
    fun data_isFetchedFromCorrectURL() {
        val url = URL("http://10.0.2.2:8080/products")
        val connection = url.openConnection() as HttpURLConnection
        assertEquals("GET", connection.requestMethod)
    }

    @Test
    fun testElementsContainerOtherXMLDisplayed() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withId(R.id.item_name)).check(matches(isDisplayed()))
            onView(withId(R.id.item_price)).check(matches(isDisplayed()))
            onView(withId(R.id.button)).check(matches(isDisplayed()))
        }
    }
}