package com.example.campusconnect

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CampusMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("CampusConnect", "From: ${remoteMessage.from}")

        remoteMessage.notification?.let {
            val channelId = "campus_connect_channel"
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "CampusConnect Notifications",
                    NotificationManager.IMPORTANCE_HIGH
                )
                manager.createNotificationChannel(channel)
            }

            val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle(it.title ?: "CampusConnect")
                .setContentText(it.body)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()

            NotificationManagerCompat.from(this).notify(1001, notification)
        }
    }

    override fun onNewToken(token: String) {
        Log.d("CampusConnect", "Refreshed token: $token")
        // Optional: send this token to your backend
    }
}
