package org.comon.chillcounselor.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import org.comon.chillcounselor.presentation.component.AnswerText
import org.comon.chillcounselor.presentation.component.BGMButton
import org.comon.chillcounselor.presentation.component.BackButton
import org.comon.chillcounselor.presentation.component.CancelButton
import org.comon.chillcounselor.presentation.component.ChillGuyImage
import org.comon.chillcounselor.presentation.component.CompleteButton
import org.comon.chillcounselor.presentation.component.CounselTextField
import org.comon.chillcounselor.presentation.component.DescriptionText
import org.comon.chillcounselor.presentation.component.FinishButton
import org.comon.chillcounselor.presentation.component.RewriteButton
import org.comon.chillcounselor.presentation.component.StartButton
import org.comon.chillcounselor.presentation.component.TitleText
import org.comon.chillcounselor.presentation.component.WriteNewButton
import org.comon.chillcounselor.presentation.ui.theme.ChillBackground
import org.comon.chillcounselor.presentation.viewmodel.CounselUiState
import org.comon.chillcounselor.presentation.viewmodel.CounselViewModel
import org.comon.presentation.R

@Composable
fun MainScreen(
    finishApp: () -> Unit,
    viewModel: CounselViewModel = viewModel(),
){
    val uiState = viewModel.counselUiState.collectAsStateWithLifecycle().value

    LaunchedEffect(uiState) {
        if(uiState == CounselUiState.SplashScreen){
            delay(3000)
            viewModel.checkNetworkState()
        } else {
            viewModel.toggleBGM()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ChillBackground)
                .padding(
                    vertical = dimensionResource(R.dimen.main_screen_column_vertical_padding),
                    horizontal = dimensionResource(R.dimen.main_screen_column_horizontal_padding)
                ),
            verticalArrangement = if(uiState == CounselUiState.SplashScreen){
                Arrangement.Center
            }else{
                Arrangement.SpaceAround
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(uiState)

            if(uiState is CounselUiState.ServerOutScreen){
                DescriptionText(uiState.message)
            }else if(uiState is CounselUiState.WrongAskScreen){
                DescriptionText(uiState.message)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChillGuyImage(
                    chillGuyState = viewModel.chillGuyState.collectAsStateWithLifecycle().value
                )

                if(uiState is CounselUiState.ResultScreen){
                    AnswerText(uiState.counselData.message)
                }
            }

            if(uiState == CounselUiState.SplashScreen || uiState == CounselUiState.LoadingScreen){
                CircularProgressIndicator(
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.circular_progress_indicator_padding_top)
                    )
                )
            }

            when(uiState){
                CounselUiState.InitialScreen -> StartButton(
                    startWrite = { viewModel.startWrite() }
                )
                CounselUiState.WriteScreen -> BackButton(
                    backToInitScreen = { viewModel.changeInToInitScreen() }
                )
                CounselUiState.LoadingScreen -> CancelButton(
                    cancelRequest = { viewModel.cancelRequest() }
                )
                is CounselUiState.ResultScreen -> {
                    Column {
                        WriteNewButton(
                            startWrite = { viewModel.startWrite() }
                        )
                        BackButton(
                            backToInitScreen = { viewModel.changeInToInitScreen() }
                        )
                    }
                }
                is CounselUiState.WrongAskScreen -> {
                    Column {
                        RewriteButton(
                            startWrite = { viewModel.startWrite() }
                        )
                        BackButton(
                            backToInitScreen = { viewModel.changeInToInitScreen() }
                        )
                    }
                }
                is CounselUiState.ServerOutScreen -> FinishButton(
                    finishApp = finishApp
                )
                else -> {}
            }
        }

        if(uiState != CounselUiState.SplashScreen){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.bgm_button_row_padding)),
                horizontalArrangement = Arrangement.End
            ) {
                BGMButton(
                    bgmState = viewModel.bgmPlayState.collectAsStateWithLifecycle().value,
                    toggleBGM = { viewModel.toggleBGM() }
                )
            }
        }

        if(uiState == CounselUiState.WriteScreen){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CounselTextField(
                    textValue = viewModel.inputCounselContent.collectAsStateWithLifecycle().value,
                    changeTextValue = {
                        viewModel.changeInputValue(it)
                    }
                )
                CompleteButton(
                    requestCounsel = { viewModel.completeCounselInput() },
                    enabled = viewModel.isInputValid.collectAsStateWithLifecycle().value
                )
            }

        }
    }

}