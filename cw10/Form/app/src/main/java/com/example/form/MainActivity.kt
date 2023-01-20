package com.example.form

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.form.databinding.ActivityMainBinding
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    var users = ArrayList<PaymentsModel>()

    var flag = false;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()

        binding.submitButton.setOnClickListener { submitForm() }
    }

    private fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun submitForm()
    {
        binding.emailContainer.helperText = validEmail()
        binding.passwordContainer.helperText = validPassword()
        binding.phoneContainer.helperText = validPhone()

        val validEmail = binding.emailEditText.text
        val validPassword = binding.passwordEditText.text
        val validPhone = binding.phoneEditText.text

        toast("Wait... Data needs to be validated...")

        var url = URL("http://10.0.2.2:8080/users")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    val parts = line.split("")
                    users.add(PaymentsModel(parts[0], parts[1], parts[2].toInt()))
                }
            }
        }

        for (user in users) {
            if(user.email == validEmail.toString() && user.password == validPassword.toString() && user.number == validPhone) flag = true;    //Moze dokonac zakupu
        }

    }

    private fun emailFocusListener()
    {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    fun validEmail(): String?
    {
        val emailText = binding.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener()
    {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String?
    {

        return null
    }

    private fun phoneFocusListener()
    {
        binding.phoneEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.phoneContainer.helperText = validPhone()
            }
        }
    }

    fun validPhone(): String?
    {
        val phoneText = binding.phoneEditText.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 9)
        {
            return "Must be 9 Digits"
        }
        return null
    }

//Testy


    @Test
    fun testBindingInitialization() {
        val activity = MainActivity()
        activity.onCreate(null)
        assertTrue(::binding.isInitialized)
    }
    @Test
    fun testUsersListIsEmpty() {
        val activity = MainActivity()
        activity.onCreate(null)
        assertTrue(activity.users.isEmpty())
    }
    @Test
    fun testFlagIsFalse() {
        val activity = MainActivity()
        activity.onCreate(null)
        assertFalse(activity.flag)
    }

}


