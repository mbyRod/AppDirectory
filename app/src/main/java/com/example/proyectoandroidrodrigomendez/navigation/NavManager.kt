package com.example.proyectoandroidrodrigomendez.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectoandroidrodrigomendez.dataStore.StoreBoarding
import com.example.proyectoandroidrodrigomendez.onBoardViews.MainOnBoarding
import com.example.proyectoandroidrodrigomendez.viewModel.DirectoryViewModel
import com.example.proyectoandroidrodrigomendez.views.AddContactView
import com.example.proyectoandroidrodrigomendez.views.EditView
import com.example.proyectoandroidrodrigomendez.views.HomeView
import com.example.proyectoandroidrodrigomendez.views.SplashView


@Composable
fun NavManager(directoryVM: DirectoryViewModel){
    val context= LocalContext.current
    val dataStore= StoreBoarding(context)
    val store=dataStore.getStoreBoarding.collectAsState(initial = true)

    val navController= rememberNavController()
    NavHost(navController=navController,
        startDestination= if(store.value==true) "home" else "Splash")
    {
        composable("onBoarding"){
            MainOnBoarding(navController,dataStore)
        }
        composable("home"){
            HomeView(navController, directoryVM)
        }
        composable("Splash"){
            SplashView(navController, store.value)
        }
        composable("AddContact"){
            AddContactView(navController, directoryVM)
        }
        composable(
            route = "EditView/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.LongType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getLong("contactId") ?: 0L
            EditView(navController, directoryVM, contactId)
        }
    }
}