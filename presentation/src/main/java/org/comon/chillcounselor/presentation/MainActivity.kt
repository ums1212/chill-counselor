package org.comon.chillcounselor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import org.comon.chillcounselor.presentation.screen.MainScreen
import org.comon.chillcounselor.presentation.ui.theme.ChillCounselorTheme
import org.comon.chillcounselor.presentation.viewmodel.CounselViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CounselViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChillCounselorTheme {
                MainScreen(
                    uiState = viewModel.counselUiState.collectAsStateWithLifecycle().value,
                    chillGuyState = viewModel.chillGuyState.collectAsStateWithLifecycle().value,
                    bgmState = viewModel.bgmPlayState.collectAsStateWithLifecycle().value,
                    textInputValue = viewModel.inputCounselContent.collectAsStateWithLifecycle().value,
                    isInputValid = viewModel.isInputValid.collectAsStateWithLifecycle().value,
                    changeIntoInitScreen = { viewModel.changeInToInitScreen() },
                    changeIntoWriteScreen = { viewModel.startWrite() },
                    requestCounsel = { viewModel.completeCounselInput() },
                    cancelRequest = { viewModel.cancelRequest() },
                    changeInputValue = { viewModel.changeInputValue(it) },
                    toggleBGM = { viewModel.toggleBGM() },
                    finishApp = {
                        viewModel.releaseBGM()
                        this.finish()
                    }
                )
            }
        }

        viewModel.checkNetworkState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.playBGM()
    }

    override fun onPause() {
        super.onPause()
        viewModel.releaseBGM()
    }
}