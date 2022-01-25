package com.example.singletapcomposesample

import android.os.SystemClock
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.singletapcomposesample.extention.mainContentPadding
import com.example.singletapcomposesample.ui.components.SingleTapButton
import com.example.singletapcomposesample.ui.theme.SingleTapComposeSampleTheme
import java.util.concurrent.TimeUnit

@Composable
fun MainContent() {
    SingleTapComposeSampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                content = {
                    SingleTapSampleScreen(modifier = Modifier.mainContentPadding(it))
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SingleTapSampleScreen(modifier: Modifier = Modifier) {
    var lastClickTime by remember { mutableStateOf(0L) }
    var resultText by remember { mutableStateOf("result Text") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = resultText)

        repeat(3) {
            SingleTapButton(
                lastClickTime = lastClickTime,
                onSingleClick = {
                    lastClickTime = SystemClock.elapsedRealtime()
                    resultText =
                        "elapsedRealtime(Minutes) ${TimeUnit.MILLISECONDS.toSeconds(lastClickTime)}"
                },
            ) {
                Text(text = "Button $it")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SingleTapComposeSampleTheme {
        MainContent()
    }
}