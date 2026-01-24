package com.manahu.manager.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manahu.manager.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

     LaunchedEffect(Unit) {
        delay(1300)
        navController.navigate("MainScreen") {
            popUpTo("Splash") {
                inclusive = true
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().systemBarsPadding(), contentAlignment = Alignment.Center){
        Image(painter = painterResource(R.drawable.fonpleten), contentDescription = "fon",
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
        )
        CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Color.Black)
    }
}