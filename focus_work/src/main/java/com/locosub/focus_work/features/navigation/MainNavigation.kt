package com.locosub.focus_work.features.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.locosub.focus_work.features.domain.ui.screen.HomeScreen
import com.locosub.focus_work.features.domain.ui.screen.InfoScreen
import com.locosub.focus_work.features.domain.ui.screen.ReportScreen
import com.locosub.focus_work.features.domain.ui.screen.TimerScreen
import com.locosub.focus_work.features.domain.ui.screen.details.AddTaskScreen


@Composable
fun MainNavigation(
    navHostController: NavHostController,
) {

    NavHost(navController = navHostController, startDestination = BottomBarScreen.Home.route ){
        composable(BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(BottomBarScreen.Timer.route){
            TimerScreen()
        }
        composable(BottomBarScreen.Report.route){
            ReportScreen()
        }
        composable(BottomBarScreen.Info.route){
            InfoScreen()
        }
    }

}