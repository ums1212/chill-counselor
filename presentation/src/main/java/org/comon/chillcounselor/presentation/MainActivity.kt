package org.comon.chillcounselor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.comon.chillcounselor.presentation.screen.MainScreen
import org.comon.chillcounselor.presentation.ui.theme.ChillCounselorTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChillCounselorTheme {
                MainScreen(
                    finishApp = { this.finish() }
                )
            }
        }
    }
}