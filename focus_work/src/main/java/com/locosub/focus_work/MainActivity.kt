package com.locosub.focus_work

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.locosub.focus_work.features.navigation.BottomBar
import com.locosub.focus_work.features.navigation.MainNavigation
import com.locosub.focus_work.ui.theme.NoDisturbanceTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
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
}
