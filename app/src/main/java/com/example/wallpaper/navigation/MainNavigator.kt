package com.example.wallpaper.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wallpaper.view.CategoryScreen
import com.example.wallpaper.view.ExploreScreen
import com.example.wallpaper.view.HomeScreen
import com.example.wallpaper.view.InstagramScreen
import com.example.wallpaper.view.OnBoardScreen
import com.example.wallpaper.view.SearchScreen
import com.example.wallpaper.view.PhotoDetail
import com.example.wallpaper.view.auth.Login
import com.example.wallpaper.view.auth.Register
import com.example.wallpaper.viewModel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.Home
import compose.icons.evaicons.fill.Search
import compose.icons.evaicons.outline.Home
import compose.icons.evaicons.outline.Person
import compose.icons.evaicons.outline.Search

sealed class Screen(
    val route: String, val label: String, val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    object HOME : Screen("home", "Home", EvaIcons.Fill.Home, EvaIcons.Outline.Home)
    object EXPLORE :
        Screen("explore", "Explore", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder)

    object SEARCH : Screen("search", "Search", EvaIcons.Fill.Search, EvaIcons.Outline.Search)
    object CATEGORY :
        Screen("category/{category}", "Category", EvaIcons.Outline.Person, EvaIcons.Outline.Person)

    object `DETAIL` : Screen(
        "photoDetail/{imageUrl}",
        "PhotoDetail",
        EvaIcons.Outline.Person,
        EvaIcons.Outline.Person
    )
    object WEBVIEW: Screen(
        "webview",
        "WebView",
        EvaIcons.Outline.Person,
        EvaIcons.Outline.Person
    )

}
sealed class AuthScreen(val route: String, val label: String) {
    object Login : AuthScreen("login", "Login")
    object Register : AuthScreen("register", "Register")
    object Onboard : AuthScreen("onBoard", "OnBoard")
    object MainNav : AuthScreen("home", "MainNav")

}


val screenList = listOf(
    Screen.HOME, Screen.SEARCH, Screen.EXPLORE
)

@Composable
fun MainNavigator(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {

    val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
        Screen.HOME.route
    } else {
        AuthScreen.Onboard.route
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        composable(Screen.HOME.route) {
            HomeScreen(navController)
        }
        composable(Screen.EXPLORE.route) {
            ExploreScreen(navController)
        }
        composable(Screen.SEARCH.route) {
            SearchScreen(navController)
        }
        composable(Screen.CATEGORY.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            CategoryScreen(category = category!!, navController = navController)
        }
        composable(
            Screen.DETAIL.route,
            arguments = listOf(navArgument("imageUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val imageUrl = backStackEntry.arguments?.getString("imageUrl")
            println(imageUrl)
            PhotoDetail(url = imageUrl!!, navController)
        }
        composable(AuthScreen.Onboard.route) {
            OnBoardScreen(navController)
        }
        composable(AuthScreen.Login.route) {
            Login(navController)
        }
        composable(AuthScreen.Register.route) {
            Register(navController)
        }
        
        composable(Screen.WEBVIEW.route) {
            InstagramScreen()
        }

    }
}