package com.locosub.focus_work.features.domain.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.locosub.focus_work.R
import com.locosub.focus_work.common.CommonButton
import com.locosub.focus_work.common.CommonRadioButton
import com.locosub.focus_work.common.CommonTextField
import com.locosub.focus_work.ui.theme.LightGrey
import com.locosub.focus_work.ui.theme.Navy

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TimerScreen() {

    val levels by remember { mutableStateOf(listOf("Level 1", "Level 2", "Level 3")) }
    var levelState by remember { mutableStateOf("Level 1") }
    var time by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text = stringResource(id = R.string.timer_screen),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGrey)
        ) {
            Column {
                Row {
                    levels.forEach {
                        CommonRadioButton(selected = it == levelState, title = it) { state ->
                            levelState = state
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        CommonTextField(
                            text = time,
                            label = stringResource(id = R.string.enter_min),
                            modifier = Modifier.width(150.dp),
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done,
                            color = Navy
                        ) {
                            time = it
                        }
                        CommonButton(
                            title = stringResource(id = R.string.set),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    if (time.isNotEmpty())
                        Text(
                            text = "$time:00 min", style = TextStyle(
                                color = Color.Black,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.W500
                            ),
                            modifier = Modifier.padding(vertical = 10.dp)
                        )

                    CommonButton(
                        title = stringResource(id = R.string.start),
                        modifier = Modifier.padding(start = 10.dp)
                    )

                }

            }
        }

    }

}