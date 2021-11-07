package com.example.notificationsapp2
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val notificationId = 1111
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countbtn = findViewById<Button>(R.id.btn)
        countbtn.setOnClickListener{

            notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val pendingIntent = PendingIntent.getActivity(this,0,Intent(this,MainActivity2::class.java),PendingIntent.FLAG_UPDATE_CURRENT)



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification
                    .Builder(this,channelId)
                    .setContentText("We Are Ready")
                    .setContentTitle("App Noti 2")
                    .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                    .setContentIntent(pendingIntent)


            }else{
                builder = Notification
                    .Builder(this)
                    .setContentText("We Are Ready")
                    .setContentTitle("App Noti 2")
                    .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                    .setContentIntent(pendingIntent)

            }

            val timercount = object : CountDownTimer(5000,500) {


                override fun onTick(p0: Long) {

                }

                override fun onFinish() {
                    notificationManager.notify(notificationId,builder.build())
                }


            }
            timercount.start()




        }



    }
}