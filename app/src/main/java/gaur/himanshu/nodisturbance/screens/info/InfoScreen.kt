package gaur.himanshu.nodisturbance.screens.info

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import gaur.himanshu.nodisturbance.model.Task
import gaur.himanshu.nodisturbance.screens.timer.TimerViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.material.*



@Destination
@Composable
fun InfoScreen(timerViewModel: TimerViewModel = hiltViewModel(), task: Task? = null) {


    Scaffold(modifier = Modifier.padding(bottom = 55.dp), topBar = {
        TopAppBar(title= { Text(text = "Info") })

    }) {
        Log.d("TAG", "InfoScreen: $it")
    }

    val names = listOf("Level 1: Only blocking notifications from all the apps","Level2 : Blocking social media notification and apps, blocking only notification from other apps for example(slack, emails, skype/zoom messages)","Level3 : Blocking social media notification and apps, block emails, messages, slack")
    LazyColumn {
        items(names) { name ->
            Text(name, modifier = Modifier.padding(40.dp) )
            Divider()
        }

    }

}