package com.example.proyectoandroidrodrigomendez.onBoardViews

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.proyectoandroidrodrigomendez.R
import com.example.proyectoandroidrodrigomendez.data.PageData
import com.example.proyectoandroidrodrigomendez.dataStore.StoreBoarding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class,
    ExperimentalFoundationApi::class)
@Composable
fun MainOnBoarding(navController: NavController,store: StoreBoarding){
    val items= ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.notebook,
            "Encuentra lo que necesitas al instante",
            "Todos tus contactos en un mismo lugar!!"
        )
    )
    items.add(
        PageData(
            R.raw.phone,
            "Todo en la pantalla de tu dispositivo",
            "Agrega, edita y visualiza contactos fácilmente. Lleva el control de tus números importantes."
        )
    )
    items.add(
        PageData(
            R.raw.cloud,
            "Tus datos, siempre contigo",
            "Tu información se guarda localmente y puedes acceder a ella sin conexión."
        )
    )


    val pagerState= rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item=items, pagerState=pagerState,modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White), navController,store
    )
}