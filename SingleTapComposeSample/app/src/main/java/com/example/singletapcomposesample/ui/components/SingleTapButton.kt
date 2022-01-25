package com.example.singletapcomposesample.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.SystemClock
import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import com.example.singletapcomposesample.ui.theme.SingleTapComposeSampleTheme

@Composable
fun SingleTapButton(
    modifier: Modifier = Modifier,
    clickDisablePeriod: Long = 1000L,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    var lastClickTime by remember { mutableStateOf(0L) }
    Button(
        modifier = modifier,
        onClick = {
            if (SystemClock.elapsedRealtime() - lastClickTime < clickDisablePeriod) {
                Log.d("SingleTapButton", "Disable click for a certain period.")
            } else {
                Log.d("SingleTapButton", "onSingleClick")
                lastClickTime = SystemClock.elapsedRealtime()
                onClick()
            }
        },
        content = content
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = PIXEL_4)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = PIXEL_4)
@Composable
fun SingleTapButtonPreview() {
    SingleTapComposeSampleTheme {
        SingleTapButton(onClick = { }) {
            Text(text = "Button 1")
        }
    }
}