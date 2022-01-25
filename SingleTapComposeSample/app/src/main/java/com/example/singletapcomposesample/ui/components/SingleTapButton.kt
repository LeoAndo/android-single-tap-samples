package com.example.singletapcomposesample.ui.components

import android.os.SystemClock
import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SingleTapButton(
    modifier: Modifier = Modifier,
    lastClickTime: Long,
    clickDisablePeriod: Long = 1000L,
    onSingleClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            if (SystemClock.elapsedRealtime() - lastClickTime < clickDisablePeriod) {
                Log.d("SingleTapButton", "Disable click for a certain period.")
            } else {
                Log.d("SingleTapButton", "onSingleClick")
                onSingleClick()
            }
        },
        content = content
    )
}