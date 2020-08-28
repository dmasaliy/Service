package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_activ2.*
import kotlinx.android.synthetic.main.activity_main.*

class Activ2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activ2)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activ2panding.setOnClickListener {

            var pendingIntent = createPendingResult(Activ2.TASK_1, Intent(), 0)
            var intent = Intent(this, MySecondService::class.java)
                .putExtra(MainActivity.PARAM_TIME, 7)
                .putExtra(PARAM_TASK, TASK_1)
            startService(intent)

            pendingIntent = createPendingResult(Activ2.TASK_2, Intent() , 0)
            intent = Intent(this, MySecondService::class.java)
                .putExtra(MainActivity.PARAM_TIME, 4)
                .putExtra(PARAM_TASK, TASK_2)
            startService(intent)

            pendingIntent = createPendingResult(Activ2.TASK_3, Intent() , 0)
            intent = Intent(this, MySecondService::class.java)
                .putExtra(MainActivity.PARAM_TIME, 6)
                .putExtra(PARAM_TASK, TASK_3)
            startService(intent)
        }

        val BroadcastReceiver = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                val task = intent.getIntExtra(PARAM_TASK, 0)
                val status = intent?.getIntExtra(PARAM_STATUS, 0)

                if(status == START_CODDE){
                    when(task){
                        TASK_1 ->task11.text = "Task 1 started"
                        TASK_2 ->task22.text = "Task 2 started"
                        TASK_3 ->task33.text = "Task 3 started"
                    }
                }


                if(status == START_CODDE){
                    val result = intent?.getIntExtra(Activ2.PARAM_RESULT, 0)
                    when(task){
                        TASK_1 ->task11.text = "Task 1 startes, result = $result"
                        TASK_2 ->task22.text = "Task 2 startes, result = $result"
                        TASK_3 ->task33.text = "Task 3 startes, result = $result"
                    }
                }

                val intentFilter = IntentFilter(BROADCAST_ACTION)

                registerReceiver( BroadcastReceiver, intentFilter)

            }

        }

    }

    companion object {
        val START_CODDE = 100
        val FINISH_CODE = 200
        val PARAM_RESULT = " kurlik"
        val PARAM_TIME = "time"
        val PARAM_TASK = "task"
        val PARAM_STATUS = "status"


        const val TASK_1 = 1
        const val TASK_2 = 2
        const val TASK_3 = 3

        const val BROADCAST_ACTION = "com.example.service.BROADCASTACTIVITY"

    }
}