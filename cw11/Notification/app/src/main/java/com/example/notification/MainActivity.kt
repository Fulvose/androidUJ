package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    // declaring variables
    private val notificationId = 101
    private val CHANNEL_ID = "channel_id_example"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            sendNotification()
        }
    }

        private fun createNotificationChannel(){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "UWAGA WIELKA DOSTAWA PRODUKTÓW!"
                val descriptionText = "Sprawdź czy znajdziesz coś dla siebie!"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply{
                    description= descriptionText
                }
                val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

        private fun sendNotification() {
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("UWAGA WIELKA DOSTAWA PRODUKTÓW!")
                    .setContentText("Sprawdź czy znajdziesz coś dla siebie!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                with(NotificationManagerCompat.from(this)){
                    notify(notificationId, builder.build())
            }
        }
    }
