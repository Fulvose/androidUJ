package com.example.login.data

import com.example.login.data.model.LoggedInUser
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            var url = URL("http://10.0.2.2:8080/users")

            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"  // optional default is GET

                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        val parts = line.split("")
                        if(parts[0] == username && parts[1] == password){
                            //logowanie
                        }
                    }
                }
            }
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jan Kowalski")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}