package com.wahidabd.mangain.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.wahidabd.mangain.BuildConfig
import com.wahidabd.mangain.R
import com.wahidabd.mangain.view.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

@AndroidEntryPoint
class FirebaseCloudMessagingService : FirebaseMessagingService() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("new FCM token retrieved $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.data.isNotEmpty()){
            val notification = generateNotification(message)
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    private fun generateNotification(message: RemoteMessage): Notification {

        // 0. Get data
        val titleText = message.data["title"]
        val mainNotificationText = message.data["body"]
        val titleTextNotification = BuildConfig.APP_NAME

        // 1. Create Notification Channel for O+ and beyond devices (26+).
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID, titleTextNotification, NotificationManager.IMPORTANCE_HIGH
            )

            // Adds NotificationChannel to system. Attempting to create an
            // existing notification channel with its original values performs
            // no operation, so it's safe to perform the below sequence.
            notificationManager.createNotificationChannel(notificationChannel)
        }

        // 2. Build the BIG_TEXT_STYLE.
        val bigTextStyle = NotificationCompat.BigTextStyle()
            .bigText(mainNotificationText)
            .setBigContentTitle(titleText)


        // 3. Set up main Intent/Pending Intents for notification.
        val launchActivityIntent = Intent(this, SplashActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            this, 0, launchActivityIntent, PendingIntent.FLAG_IMMUTABLE or 0
        )

        val cancelIntent = Intent(this, FirebaseCloudMessagingService::class.java)
        cancelIntent.putExtra("${applicationContext.packageName}.extra.CANCEL_NOTIFICATION", true)

        val servicePendingIntent = PendingIntent.getService(
            this, 0, cancelIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )


        // 4. Build and issue the notification.
        // Notification Channel Id is ignored for Android pre O (26).
        val notificationCompatBuilder =
            NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)

        return notificationCompatBuilder
            .setStyle(bigTextStyle)
            .setVibrate(LongArray(1))
            .setContentTitle(titleTextNotification)
            .setContentText(mainNotificationText)
            .setSmallIcon(R.drawable.ic_logo_small)
            .setDefaults(NotificationCompat.DEFAULT_SOUND)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .addAction(
//                R.drawable.ic_map_location,
//                "Open",
//                activityPendingIntent
//            )
//            .addAction(
//                R.drawable.ic_arrow_back,
//                "Stop",
//                servicePendingIntent
//            )
            .setContentIntent(activityPendingIntent)
            .build()

    }


    companion object {

        private const val NOTIFICATION_ID = 123412421

        private const val NOTIFICATION_CHANNEL_ID = "com.wahidabd.mangain.notification_channel"

    }

}