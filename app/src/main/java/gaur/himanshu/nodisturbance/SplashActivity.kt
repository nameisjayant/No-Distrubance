package gaur.himanshu.nodisturbance

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.locosub.focus_work.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.nodisturbance.ui.theme.NoDisturbanceTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoDisturbanceTheme {

                LaunchedEffect(key1 = true){
                    delay(2000)
                    startActivity(
                        Intent(this@SplashActivity,MainActivity::class.java)
                    )
                    finish()
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0XFF112B3C)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "No Disturbance", style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}
