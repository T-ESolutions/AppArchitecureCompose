package app.te.core.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() = slideIntoContainer(
    AnimatedContentTransitionScope.SlideDirection.Left,
    animationSpec = tween(700)
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() = slideOutOfContainer(
    AnimatedContentTransitionScope.SlideDirection.Left,
    animationSpec = tween(700)
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition() =
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(700)
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition() =
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(700)
    )
