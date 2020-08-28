package com.example.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MySecondService : Service() {

    var executorService : ExecutorService? = null
    val TAG = " MyService"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        executorService = Executors.newFixedThreadPool(3)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        val time = intent.getIntExtra(Activ2.PARAM_TIME, 1)
        val task = intent.getIntExtra(Activ2.PARAM_TASK, 0)
        val myRun = MyRun(time, startId, task)
        executorService?.execute(myRun)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDEstroy: ")
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    inner class MyRun(var time: Int, var startId : Int, var task: Int) : Runnable{

        override fun run() {
            Log.d(TAG, "MyRun time = $time startId = $startId")
            val intent = Intent(Activ2.BROADCAST_ACTION)
                .putExtra(Activ2.PARAM_STATUS, Activ2.START_CODDE)
                .putExtra(Activ2.PARAM_TASK,task)
            sendBroadcast(intent)
           // pendingIntent.send(MainActivity.START_CODDE)
            TimeUnit.SECONDS.sleep(time.toLong())

            intent.putExtra(Activ2.PARAM_STATUS, Activ2.FINISH_CODE)
            intent.putExtra(Activ2.PARAM_RESULT, time * 1000)

            stop()
        }

        fun stop(){
            Log.d(TAG, "MyRun $startId finished ${stopSelfResult(startId)}")
        }

    }
}
