package org.comon.chillcounselor.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.comon.chillcounselor.presentation.component.BGMButton
import org.comon.chillcounselor.presentation.component.CancelButton
import org.comon.chillcounselor.presentation.component.ChillGuyImage
import org.comon.chillcounselor.presentation.component.CompleteButton
import org.comon.chillcounselor.presentation.component.FinishButton
import org.comon.chillcounselor.presentation.component.RewriteButton
import org.comon.chillcounselor.presentation.component.StartButton
import org.comon.chillcounselor.presentation.component.TitleText
import org.comon.chillcounselor.presentation.component.WriteNewButton
import org.comon.chillcounselor.presentation.viewmodel.CounselUiState
import org.comon.chillcounselor.presentation.viewmodel.CounselViewModel
import org.comon.presentation.R

@Composable
fun MainScreen(
    viewModel: CounselViewModel = viewModel(),
    finishApp: () -> Unit,
){
    val uiState = viewModel.counselUiState.collectAsStateWithLifecycle()

    if(uiState.value!=CounselUiState.SplashScreen){
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            BGMButton(
                toggleBGM = {}
            )
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if(uiState.value == CounselUiState.SplashScreen){
            Arrangement.Center
        }else{
            Arrangement.SpaceBetween
        },
        modifier = Modifier.fillMaxSize()
    ) {
        TitleText(
            title = stringResource(R.string.loading_text_splash_screen)
        )

        ChillGuyImage(
            size = 150.dp
        )

        CircularProgressIndicator(
            modifier = Modifier.padding(top = 24.dp)
        )

        when(uiState.value){
            CounselUiState.InitialScreen -> StartButton(
                startWrite = { viewModel.startWrite() }
            )
            CounselUiState.WriteScreen -> CompleteButton(
                requestCounsel = { viewModel.requestCounsel() }
            )
            CounselUiState.LoadingScreen -> CancelButton(
                cancelRequest = { viewModel.cancelRequest() }
            )
            is CounselUiState.ResultScreen -> WriteNewButton(
                backToInitScreen = { viewModel.backToInitScreen() }
            )
            is CounselUiState.WrongAskScreen -> RewriteButton(
                startWrite = { viewModel.startWrite() }
            )
            is CounselUiState.ServerOutScreen -> FinishButton(
                finishApp = finishApp
            )
            else -> {}
        }
    }
}