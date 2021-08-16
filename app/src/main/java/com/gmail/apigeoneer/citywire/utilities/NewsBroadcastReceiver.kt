package com.gmail.apigeoneer.citywire.utilities

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.gmail.apigeoneer.citywire.R

class NewsBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
//        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
//            // Set the alarm here.
//        }

        // Here we can do any task that needs to be done at a specific time everyday
        val notificationManager=ContextCompat.getSystemService(context!!,
            NotificationManager::class.java) as NotificationManager

        notificationManager.sendNotification("Did you get the new???", context)

        Log.d(TAG, "Notification just sent");
    }

    companion object {
        private const val TAG="NewsBroadcastReceiver"
    }
}