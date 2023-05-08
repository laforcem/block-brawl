package edu.mines.csci448.pcm.blockbrawl

import android.app.Activity
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.startActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BlockBrawlAlarmReceiver : BroadcastReceiver() {

    companion object{
        private const val LOG_TAG = "448.Alarm"
        private const val ALARM_ACTION = "448_ACTION"
        private const val CHANNEL_ID = "BlockBrawlNotificationID"
        private const val CHANNEL_NAME = "BlockBrawlChannel"
        private const val CHANNEL_DESC = "This channel is used to notify a user to come play again some time soon"

        private fun createIntent(context: Context): Intent{
            val intent = Intent(context, BlockBrawlAlarmReceiver::class.java).apply {
                action = ALARM_ACTION
            }
            return intent
        }
    }

    private fun scheduleAlarm(activity: Activity) {
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = createIntent(activity)
        val pendingIntent = PendingIntent.getBroadcast(
            activity,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val alarmDelayInSeconds = 86400
        val alarmInUTC = System.currentTimeMillis() + alarmDelayInSeconds * 1_000L
        Log.d(LOG_TAG, "Setting alarm for ${SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US).format(Date(alarmInUTC))}")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d(LOG_TAG, "running on Version S or newer, checking if can schedule exact alarms")
            if(alarmManager.canScheduleExactAlarms()) {
                Log.d(LOG_TAG, "can schedule exact alarms")
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                                                        alarmInUTC,
                                                        pendingIntent)
            } else {
                Log.d(LOG_TAG, "can't schedule exact alarms, launching intent to bring up settings")
                val settingsIntent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(activity, settingsIntent, null)
            }
        } else {
            Log.d(LOG_TAG, "running on Version R or older, can set alarm directly")
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                                                    alarmInUTC,
                                                    pendingIntent)
        }
    }

    fun checkPermissionAndScheduleAlarm(
        activity: Activity,
        permissionLauncher: ActivityResultLauncher<String>
    ) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d(LOG_TAG, "running on Version Tiramisu or newer, need permission")
            if(activity.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, "have notification permission")
                scheduleAlarm(activity)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.POST_NOTIFICATIONS)) {
                    Log.d(LOG_TAG, "previously denied notification permission")
                } else {
                    Log.d(LOG_TAG, "request notification permission")
                    permissionLauncher.launch( android.Manifest.permission.POST_NOTIFICATIONS )
                }
            }
        } else {
            Log.d(LOG_TAG, "running on Version S or older, post away")
            scheduleAlarm(activity)
        }
    }

    fun requestPermission(
        activity: Activity,
        permissionLauncher: ActivityResultLauncher<String>
    ) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d(LOG_TAG, "running on Version Tiramisu or newer, need permission")
            if(activity.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, "have notification permission")

            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.POST_NOTIFICATIONS)) {
                    Log.d(LOG_TAG, "previously denied notification permission")
                } else {
                    Log.d(LOG_TAG, "request notification permission")
                    permissionLauncher.launch( android.Manifest.permission.POST_NOTIFICATIONS )
                }
            }
        } else {
            Log.d(LOG_TAG, "running on Version S or older, post away")

        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(LOG_TAG, "received alarm for action ${intent.action}")
        if(intent.action == ALARM_ACTION) {
            Log.d(LOG_TAG, "received our intent to schedule a reminder")

            if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                Log.d(LOG_TAG, "have permission to post notifications")
                val notificationManager = NotificationManagerCompat.from(context)

                val channel =
                    NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH
                    ).apply {
                        description = CHANNEL_DESC
                    }

                notificationManager.createNotificationChannel(channel)

                val notification = NotificationCompat.Builder(context, channel.id)
                    .setSmallIcon(R.drawable.baseline_extension_24)
                    .setContentTitle("Hey it's been a while...")
                    .setContentText("You should come brawl!")
                    .build()

                notificationManager.notify(0, notification)
            }
        }
    }
}