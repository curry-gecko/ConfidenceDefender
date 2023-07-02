package io.github.currygecko.confidencekeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.github.currygecko.confidencekeeper.ui.compose.list.AppInfoListView
import io.github.currygecko.confidencekeeper.ui.theme.ConfidenceKeeperTheme
import io.github.currygecko.confidencekeeper.usecase.AppInformationUseCase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfidenceKeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list = AppInformationUseCase().getApplicationInfo(applicationContext)
                    AppInfoListView(pagingItems = list)
                }
            }
        }
    }
}
