package com.gmail.apigeoneer.citywire.utilities

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.gmail.apigeoneer.citywire.NewsActivity
import com.gmail.apigeoneer.citywire.R

private const val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(
    messageBody: String,
    applicationContext: Context,
    status: String
) {
    // Since building the notification uses the pending intent, the intents come first
    val contentIntent = Intent(applicationContext, NewsActivity::class.java)
    // passing values to the Detail Fragment
    contentIntent.apply {
        putExtra("repoSelected", messageBody)
        putExtra("status", status)
    }

    val contentPendingIntent =  PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val loadImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.news
    )

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(loadImage)
        .bigLargeIcon(null)

    // Build the notification
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.download_channel_id)
    )
        .setPriority(NotificationCompat.PRIORITY_HIGH)

        .setSmallIcon(R.drawable.news)
        .setContentTitle(applicationContext
            .getString(R.string.notification_title))
        .setContentText(messageBody)

        .setContentIntent(contentPendingIntent)

        .setStyle(bigPicStyle)
        .setLargeIcon(loadImage)

//        .addAction(
//            R.drawable.news,
//            "View details",
//            contentPendingIntent
//        )

        .setAutoCancel(true)                   // delete the notification after it has been clicked

    // Deliver the notification
    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}