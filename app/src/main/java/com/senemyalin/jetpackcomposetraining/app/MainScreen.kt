package com.senemyalin.jetpackcomposetraining.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.senemyalin.jetpackcomposetraining.navigation.BottomBarScreen
import com.senemyalin.jetpackcomposetraining.navigation.BottomNavGraph
import com.senemyalin.jetpackcomposetraining.ui.theme.DarkGreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomBar(currentDestination, onClick = {
                navController.navigate(it.route) {
                    popUpTo(navController.graph.id)
                    launchSingleTop = true
                }
            })
        }
    ) {
        BottomNavGraph(navController = navController, it)
    }
}

@Composable
fun BottomBar(
    currentDestination: NavDestination?,
    onClick: (screen: BottomBarScreen) -> Unit
) {
    val screens = listOf(
        BottomBarScreen.Category,
        BottomBarScreen.RandomMeal,
        BottomBarScreen.Search,
    )

    BottomNavigation(
        backgroundColor = DarkGreen,
        contentColor = Color.White
    ) {

        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                onClick = { onClick(screen) }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    onClick: () -> Unit
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = "Navigation Icon",
                modifier = Modifier.size(24.dp)
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } ?: true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            onClick.invoke()
        },
    )
}
