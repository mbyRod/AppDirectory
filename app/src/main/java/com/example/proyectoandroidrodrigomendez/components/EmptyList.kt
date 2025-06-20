package com.example.proyectoandroidrodrigomendez.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoandroidrodrigomendez.R
import com.example.proyectoandroidrodrigomendez.onBoardViews.LoaderData

@Composable
fun EmptyList(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoaderData(
                modifier = Modifier,
                image = R.raw.empty_list
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}