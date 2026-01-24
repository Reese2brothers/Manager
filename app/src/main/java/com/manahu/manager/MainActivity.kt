package com.manahu.manager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.stakmanagenment.staknliero.ScreenStatusBar

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenStatusBar(colorResource(id = R.color.black))
            val navController = rememberNavController()
            NavGraphNavigate(navController)
        }
    }
}
