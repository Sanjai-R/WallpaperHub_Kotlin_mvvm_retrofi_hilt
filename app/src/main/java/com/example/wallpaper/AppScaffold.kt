package com.example.wallpaper

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wallpaper.navigation.AuthScreen
import com.example.wallpaper.navigation.MainNavigator
import com.example.wallpaper.navigation.screenList
import com.example.wallpaper.viewModel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(bottomBar = {
        if (shouldDisplayBottomBar(currentRoute)) {
            BottomNavigation(navController = navController, currentRoute = currentRoute)
        }

    }) { innerPadding ->
        MainNavigator(navController = navController, innerPadding = innerPadding)
    }
}


@Composable
fun BottomNavigation(navController: NavController, currentRoute: String?) {

    val authViewModel = hiltViewModel<AuthViewModel>()
    LaunchedEffect(key1 = authViewModel.isAuthenticated, block = {
        if (authViewModel.isAuthenticated) {
            navController.navigate(AuthScreen.Login.route)
        }
    })
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(8.dp)
            .clip(
                MaterialTheme.shapes.medium
            ),

        contentColor = MaterialTheme.colorScheme.onBackground,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 5.dp,
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                screenList.forEach { screen ->
                    BottomNavigationItem(
                        currentRoute = currentRoute,
                        screen = screen,
                        navController = navController
                    )
                }


                Column(

                    modifier = Modifier
                        .clip(
                            MaterialTheme.shapes.medium
                        )
                        .width(80.dp)
                        .height(60.dp)
                        .clickable {
                            authViewModel.signOut(navController)


                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ExitToApp,
                        contentDescription = "Logout"
                    )
                    Text(
                        "Logout",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                }

            }

        }
    )
}

@Composable
fun BottomNavigationItem(
    currentRoute: String?,
    screen: com.example.wallpaper.navigation.Screen,
    navController: NavController,
) {
    Column(

        modifier = Modifier
            .clip(
                MaterialTheme.shapes.medium
            )
            .width(80.dp)
            .height(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (currentRoute == screen.route) screen.selectedIcon else screen.unselectedIcon,
            contentDescription = screen.label,
            tint = if (currentRoute == screen.route) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .clickable {
                    println(screen.route)
                    Log.d("TAG", "BottomNavigation: ${screen.route}")
                    navController.navigate(screen.route)
                }
        )
        Text(
            screen.label,
            fontSize = 12.sp,
            color = if (currentRoute == screen.route) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
        )

    }
}

fun shouldDisplayBottomBar(currentRoute: String?): Boolean {
    val flag = currentRoute in listOf(
        com.example.wallpaper.navigation.Screen.DETAIL.route,
        AuthScreen.Login.route,
        AuthScreen.Register.route,
        AuthScreen.Onboard.route
    )
    return !flag
}