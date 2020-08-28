package com.example.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pendingService.setOnClickListener {

            var pendingIntent = createPendingResult(TASK_1, Intent(), 0)
            var intent = Intent(this, MyService::class.java)
                .putExtra(PARAM_TIME, 7)
                .putExtra(PARAM_PENDING, pendingIntent)
            startService(intent)

            pendingIntent = createPendingResult(TASK_2, Intent() , 0)
            intent = Intent(this, MyService::class.java)
                .putExtra(PARAM_TIME, 4)
                .putExtra(PARAM_PENDING, pendingIntent)
            startService(intent)

            pendingIntent = createPendingResult(TASK_3, Intent() , 0)
            intent = Intent(this, MyService::class.java)
                .putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_PENDING, pendingIntent)
            startService(intent)
        }

        activ2.setOnClickListener{
            startActivity(Intent(this, Activ2:: class.java))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == START_CODDE){
           when(requestCode){
               TASK_1 -> task1.text = "Task 1 started"
               TASK_2 -> task2.text = "Task 2 started"
               TASK_3 -> task3.text = "Task 3 started"
           }
        }

        if(resultCode == FINISH_CODE){
            val result = data?.getIntExtra(PARAM_RESULT, 0)
            when(requestCode){
                TASK_1 -> task1.text = "Task 1 hinished, result = $result"
                TASK_2 -> task2.text = "Task 2 hinished, result = $result"
                TASK_3 -> task3.text = "Task 3 hinished, result = $result"
            }
        }
    }


    companion object {
        val START_CODDE = 100
        val FINISH_CODE = 200
        val PARAM_RESULT = " kurlik"
        val PARAM_TIME = "time"
        val PARAM_PENDING = "pending"

        const val TASK_1 = 1
        const val TASK_2 = 2
        const val TASK_3 = 3

    }
}