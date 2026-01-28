package com.veracruzensei.technicalmorty.ui.core.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val FontPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val BackgroundPrimaryColor
@Composable
get() = if (isSystemInDarkTheme()) Color.Black else Color.White

val UnfocusedContainerColorTextField
@Composable
get() = if (isSystemInDarkTheme()) Color(0xFF29382E) else Color.White

val ButtonColor = Color(0xFF0DF269)