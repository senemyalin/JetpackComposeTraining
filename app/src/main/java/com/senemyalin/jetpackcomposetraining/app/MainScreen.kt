package com.senemyalin.jetpackcomposetraining.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.senemyalin.jetpackcomposetraining.navigation.BottomBarScreen
import com.senemyalin.jetpackcomposetraining.navigation.BottomNavGraph
import com.senemyalin.jetpackcomposetraining.presentation.screens.randommeal.navigation.randomMealNavigationRoute
import com.senemyalin.jetpackcomposetraining.ui.theme.DarkGreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController, it)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Category,
        BottomBarScreen.RandomMeal,
        BottomBarScreen.Search,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = DarkGreen,
        contentColor = Color.White
    ) {

        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val lastNavBarRoute = rememberSaveable { mutableStateOf("") }

    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = "Navigation Icon",
                modifier = Modifier
                    .scale(0.5f)
                    .matchColumnSize()
            )
        },
        selected = isSelected(currentDestination?.hierarchy, screen.route, lastNavBarRoute),
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                lastNavBarRoute.value = screen.route
                popUpTo(navController.graph.id)
                launchSingleTop = true
            }
        },
    )
}
@Composable
fun isSelected(
    hierarchy: Sequence<NavDestination>?,
    screenRoute: String,
    lastNavBarRoute: MutableState<String>
): Boolean {
    val isSelected = hierarchy?.any { it.route == screenRoute } ?: false

    if (isSelected) {
//        lastNavBarRoute.value = screenRoute
        return true
    }
//    else{
//        if(lastNavBarRoute.value == screenRoute){
//            return true
//        }
//    }

    return false
}

fun Modifier.matchColumnSize(): Modifier {
    return layout { measurable, constraints ->
        if (constraints.maxHeight == Constraints.Infinity) {
            layout(0, 0) {}
        } else {
            val placeable = measurable.measure(constraints)
            layout(placeable.width, placeable.height) {
                placeable.place(0, 50)
            }
        }
    }
}