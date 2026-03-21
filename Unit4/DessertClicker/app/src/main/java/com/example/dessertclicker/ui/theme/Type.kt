package com.example.dessertclicker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dessertclicker.R

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold)
)
val Typography = Typography(
    headlineLarge = TextStyle( // Total Revenue $155
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    titleMedium = TextStyle( // Title & Desserts sold 18
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    )
)
