package com.example.singletapcomposesample.extention

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

fun Modifier.mainContentPadding(paddingValues: PaddingValues): Modifier {
    return fillMaxSize().padding(
        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 12.dp,
        top = paddingValues.calculateTopPadding() + 12.dp,
        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 12.dp,
        bottom = paddingValues.calculateBottomPadding() + 12.dp,
    )
}