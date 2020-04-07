package com.boss.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivityLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "OnCreate called")
        button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onStart() {
        Log.d(TAG, "OnStart called");
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "OnResume called");
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "OnPause called");
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "OnStop called");
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "OnDestroy called");
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d(TAG, "OnRestart called");
        super.onRestart()
    }
}