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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
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
import org.comon.chillcounselor.presentation.viewmodel.ChillGuyState
import org.comon.chillcounselor.presentation.viewmodel.CounselUiState
import org.comon.presentation.R

@Composable
fun MainScreen(
    uiState: CounselUiState,
    chillGuyState: ChillGuyState,
    bgmState: Boolean,
    textInputValue: String,
    isInputValid: Boolean,
    changeIntoInitScreen: () -> Unit,
    changeIntoWriteScreen: () -> Unit,
    requestCounsel: () -> Unit,
    cancelRequest: () -> Unit,
    changeInputValue: (String) -> Unit,
    toggleBGM: () -> Unit,
    finishApp: () -> Unit,
){

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
                    chillGuyState = chillGuyState
                )

                if(uiState is CounselUiState.ResultScreen){
                    AnswerText(uiState.counselData.message)
                }
            }

            when(uiState){
                CounselUiState.SplashScreen -> {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(
                            top = dimensionResource(R.dimen.circular_progress_indicator_padding_top)
                        )
                    )
                }
                CounselUiState.InitialScreen -> StartButton(
                    startWrite = changeIntoWriteScreen
                )
                CounselUiState.WriteScreen -> BackButton(
                    backToInitScreen = changeIntoInitScreen
                )
                CounselUiState.LoadingScreen -> {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(
                            top = dimensionResource(R.dimen.circular_progress_indicator_padding_top)
                        )
                    )
                    CancelButton(
                        cancelRequest = cancelRequest
                    )
                }
                is CounselUiState.ResultScreen -> {
                    Column {
                        WriteNewButton(
                            startWrite = changeIntoWriteScreen
                        )
                        BackButton(
                            backToInitScreen = changeIntoInitScreen
                        )
                    }
                }
                is CounselUiState.WrongAskScreen -> {
                    Column {
                        RewriteButton(
                            startWrite = changeIntoWriteScreen
                        )
                        BackButton(
                            backToInitScreen = changeIntoInitScreen
                        )
                    }
                }
                is CounselUiState.ServerOutScreen -> FinishButton(
                    finishApp = finishApp
                )
            }
        }

        if(uiState != CounselUiState.SplashScreen){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.bgm_button_row_padding)),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = stringResource(R.string.bgm_copyright),
                    fontSize = dimensionResource(R.dimen.copyright_text_size).value.sp
                )
                BGMButton(
                    bgmState = bgmState,
                    toggleBGM = toggleBGM
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
                    textValue = textInputValue,
                    changeTextValue = changeInputValue
                )
                CompleteButton(
                    requestCounsel = requestCounsel,
                    enabled = isInputValid
                )
            }

        }
    }

}