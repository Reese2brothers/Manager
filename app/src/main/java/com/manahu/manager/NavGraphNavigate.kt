package com.manahu.manager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manahu.manager.screens.MainScreen
import com.manahu.manager.screens.SplashScreen
import kotlinx.coroutines.delay


private const val ANIMATION_DURATION = 100
val fastEnterTransition = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tween(ANIMATION_DURATION)
) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
val fastExitTransition = slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = tween(ANIMATION_DURATION)
) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
val fastPopEnterTransition = slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(ANIMATION_DURATION))+ fadeIn(animationSpec = tween(ANIMATION_DURATION))
val fastPopExitTransition = slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(ANIMATION_DURATION)) + fadeOut(animationSpec = tween(ANIMATION_DURATION))

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavGraphNavigate(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "SplashScreen",
        enterTransition = { fastEnterTransition },
        exitTransition = { fastExitTransition },
        popEnterTransition = { fastPopEnterTransition },
        popExitTransition = { fastPopExitTransition }
    ) {
        composable("SplashScreen") { SplashScreen(navController = navController) }
        composable("MainScreen") { MainScreen(navController = navController) }

    }
}
