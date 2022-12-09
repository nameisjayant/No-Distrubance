package com.locosub.focus_work

import android.R
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.locosub.focus_work.features.navigation.BottomBar
import com.locosub.focus_work.features.navigation.MainNavigation
import com.locosub.focus_work.ui.theme.NoDisturbanceTheme
import com.locosub.focus_work.utils.BoardCastService
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoDisturbanceTheme {
                val navHostController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navHostController)
                    },
                ) {
                    MainNavigation(navHostController = navHostController)
                }
            }
        }
    }
//
//    fun handleCancelTimer() {
//        val intent = Intent(this, BoardCastService::class.java)
//        stopService(intent)
//    }
//
//    fun handleStartTimer() {
//        val intent = Intent(this, BoardCastService::class.java)
//        intent.putExtra("inputExtra", "")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            ContextCompat.startForegroundService(this, intent)
//        } else {
//            startService(intent)
//        }
//        Log.i("main", "timerStarted")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        registerReceiver(broadcastReceiver, IntentFilter(BoardCastService.COUNTDOWN_BR))
//        Log.i("main", "Registered broadcast receiver")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        unregisterReceiver(broadcastReceiver)
//        Log.i("main", "Unregistered broadcast receiver")
//    }
//
//    override fun onStop() {
//        try {
//            unregisterReceiver(broadcastReceiver)
//        } catch (e: Exception) {
//            // Receiver was probably already stopped in onPause()
//        }
//        super.onStop()
//    }
//
//    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            if (intent!!.extras != null) {
//                val millisUntilFinished = intent.getLongExtra("countdown", 0)
//                val seconds = millisUntilFinished / 1000 % 60
//                val minutes = millisUntilFinished / (1000 * 60) % 60
//                val hours = millisUntilFinished / (1000 * 60 * 60) % 60
//                val time = "$hours : $minutes : $seconds"
//                Log.i("main", "$time")
//                val countdownTimerRunning = intent.getBooleanExtra("countdownTimerRunning", false)
//                if (countdownTimerRunning) {
//                    Log.i("main", "CountdownTimerRunning")
//                } else {
//                    Log.i("main", "CountdownTimerNotRunning")
//                }
//                val countdownTimerFinished = intent.getBooleanExtra("countdownTimerFinished", false)
//                if (countdownTimerFinished) {
//                    Log.i("main", "Finished")
//                } else {
//                    Log.i("main", "UnFinished")
//                }
//            }
//        }
//    }
}
