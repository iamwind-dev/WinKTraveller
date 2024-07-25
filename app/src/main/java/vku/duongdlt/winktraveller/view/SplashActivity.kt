package vku.duongdlt.winktraveller.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import vku.duongdlt.winktraveller.MainActivity
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.ui.theme.WinKTravellerTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WinKTravellerTheme {

                    SplashScreen()

            }

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },3000)
        }
    }
}




@Composable
fun SplashScreen(){
    val customFontFamily = FontFamily(
        Font(R.font.custom, FontWeight.Normal) // Thay thế custom_font bằng tên file thực tế
    )
   Box(
       modifier = Modifier
           .fillMaxSize()
           .background(Color(13, 110, 253)),
       contentAlignment = Alignment.Center
       ){
       Text(
           text = "WinKTraveller",
           color = Color.White,
           modifier = Modifier.align(Alignment.Center),
           fontSize = 40.sp,
           fontWeight = FontWeight.Bold,
           fontFamily = customFontFamily
       )
   }
}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WinKTravellerTheme {
//        Greeting("Android")
//    }
//}