package com.locosub.focus_work.utils

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.*

class TimerActivity : ComponentActivity() {

    private val START_TIME_IN_MILLIS: Long = 600000
    private lateinit var countDownTimer: CountDownTimer
    private var mTimeLeftInMills: Long = START_TIME_IN_MILLIS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }

    private fun updateCountDownText(): String {
        val minutes = (mTimeLeftInMills / 1000).toInt() / 60
        val seconds = (mTimeLeftInMills / 1000).toInt() % 60

        return java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

    }


}
