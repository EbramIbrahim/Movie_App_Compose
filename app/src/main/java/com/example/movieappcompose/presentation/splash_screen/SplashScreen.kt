package com.example.movieappcompose.presentation.splash_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappcompose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            2000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000L)
        navController.popBackStack()
        navController.navigate("Main_Root")
    }

    Splash(alpha = alphaAnimation.value)

}


@Composable
fun Splash(alpha: Float) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme())
                    Color.Black
                else
                    Color.White
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .alpha(alpha = alpha).size(70.dp),
            painter = painterResource(id = R.drawable.movie),
            contentDescription = "splashMovie",
        )

    }


}












