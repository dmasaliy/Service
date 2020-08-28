package com.example.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MyService : Service() {
    var executorService : ExecutorService? = null
    val TAG = " MyService"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        executorService = Executors.newFixedThreadPool(3)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        val time = intent.getIntExtra(MainActivity.PARAM_TIME, 1)
        val pendingIntent = intent.getParcelableExtra<PendingIntent>(MainActivity.PARAM_PENDING)
        val myRun = MyRun(time, startId, pendingIntent!!)
        executorService?.execute(myRun)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    inner class MyRun(var time: Int, var startId : Int, var pendingIntent: PendingIntent) : Runnable{

        val TAG = "MyService"

        override fun run() {
           Log.d(TAG, "MyRun time = $time startId = $startId")
            pendingIntent.send(MainActivity.START_CODDE)
            TimeUnit.SECONDS.sleep(time.toLong())
            val intent = Intent().putExtra(MainActivity.PARAM_RESULT, time * 1000)
            pendingIntent.send(this@MyService, MainActivity.FINISH_CODE, intent)
            stop()
        }

        fun stop(){
            Log.d(TAG, "MyRun $startId finished ${stopSelfResult(startId)}")
        }

    }

}

