package com.example.singletapcomposesample

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.singletapcomposesample.extention.mainContentPadding
import com.example.singletapcomposesample.ui.sample.SingleTapSampleScreen
import com.example.singletapcomposesample.ui.theme.SingleTapComposeSampleTheme

@Composable
fun MainContent() {
    SingleTapComposeSampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar {
                        Text(text = "SingleTapSample")
                    }
                },
                content = {
                    SingleTapSampleScreen(modifier = Modifier.mainContentPadding(it))
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_4
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4
)
@Composable
fun MainContentPreview() {
    MainContent()
}