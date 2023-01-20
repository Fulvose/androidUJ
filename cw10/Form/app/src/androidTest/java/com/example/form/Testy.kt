package com.example.form

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection
import java.net.URL

@RunWith(AndroidJUnit4::class)
class Testy {

    @Test
    fun testPhoneContainerDisplayed() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withId(R.id.phoneContainer)).check(matches(isDisplayed()))
            onView(withId(R.id.phoneEditText)).check(matches(isDisplayed()))
        }
    }
    @Test
    fun testPasswordContainerDisplayed() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withId(R.id.passwordContainer)).check(matches(isDisplayed()))
            onView(withId(R.id.passwordEditText)).check(matches(isDisplayed()))
        }
    }
    @Test
    fun testEmailContainerDisplayed() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withId(R.id.emailContainer)).check(matches(isDisplayed()))
            onView(withId(R.id.emailEditText)).check(matches(isDisplayed()))
        }
    }
    @Test
    fun testCounterEnabled() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val passwordContainer = activity.findViewById<TextInputLayout>(R.id.passwordContainer)
            assertTrue(passwordContainer.isCounterEnabled)
        }
    }
    @Test
    fun testCounterMaxLength() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val passwordContainer = activity.findViewById<TextInputLayout>(R.id.passwordContainer)
            assertEquals(16, passwordContainer.counterMaxLength)
        }
    }
    @Test
    fun testPasswordToggleEnabled() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val passwordContainer = activity.findViewById<TextInputLayout>(R.id.passwordContainer)
            assertTrue(passwordContainer.isPasswordVisibilityToggleEnabled)
        }
    }
    @Test
    fun testEmailFocusListener() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val emailEditText = activity.findViewById<TextInputEditText>(R.id.emailEditText)
            emailEditText.requestFocus()
            emailEditText.clearFocus()
            val emailContainer = activity.findViewById<TextInputLayout>(R.id.emailContainer)
            assertNotNull(emailContainer.helperText)
        }
    }
    @Test
    fun testValidEmail() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val emailEditText = activity.findViewById<TextInputEditText>(R.id.emailEditText)
            emailEditText.setText("invalidEmail")
            val helperText = activity.validEmail()
            assertEquals("Invalid Email Address", helperText)
        }
    }
    @Test
    fun testPasswordFocusListener() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val passwordEditText = activity.findViewById<TextInputEditText>(R.id.passwordEditText)
            passwordEditText.requestFocus()
            passwordEditText.clearFocus()
            val passwordContainer = activity.findViewById<TextInputLayout>(R.id.passwordContainer)
            assertNotNull(passwordContainer.helperText)
        }
    }
    @Test
    fun testValidPhone() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val phoneEditText = activity.findViewById<TextInputEditText>(R.id.phoneEditText)
            phoneEditText.setText("12345678")
            val helperText = activity.validPhone()
            assertEquals("Must be 9 Digits", helperText)
        }
    }
    @Test
    fun testPhoneFocusListener() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity -> val phoneEditText = activity.findViewById<TextInputEditText>(R.id.phoneEditText)
            phoneEditText.requestFocus()
            phoneEditText.clearFocus()
            val phoneContainer = activity.findViewById<TextInputLayout>(R.id.phoneContainer)
            assertNotNull(phoneContainer.helperText)
        }
    }
    @Test
    fun testEmailEditText() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val emailEditText = activity.findViewById<TextInputEditText>(R.id.emailEditText)
            assertNotNull(emailEditText)
        }
    }
    @Test
    fun testPasswordEditText() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val passwordEditText = activity.findViewById<TextInputEditText>(R.id.passwordEditText)
            assertNotNull(passwordEditText)
        }
    }
    @Test
    fun testPhoneEditText() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val phoneEditText = activity.findViewById<TextInputEditText>(R.id.phoneEditText)
            assertNotNull(phoneEditText)
        }
    }
    @Test
    fun testEmailContainer() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val emailContainer = activity.findViewById<TextInputLayout>(R.id.emailContainer)
            assertNotNull(emailContainer)
        }
    }
    @Test
    fun testPasswordContainer() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            val passwordContainer = activity.findViewById<TextInputLayout>(R.id.passwordContainer)
            assertNotNull(passwordContainer)
        }
    }

    @Test
    fun data_isFetchedFromCorrectURL() {
        val url = URL("http://10.0.2.2:8080/users")
        val connection = url.openConnection() as HttpURLConnection
        assertEquals("GET", connection.requestMethod)
    }
}