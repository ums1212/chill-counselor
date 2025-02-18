package org.comon.chillcounselor.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import org.comon.chillcounselor.presentation.viewmodel.CounselUiState
import org.comon.presentation.R

@Composable
fun TitleText(uiState: CounselUiState){
    Text(
        text = when(uiState){
            CounselUiState.SplashScreen -> stringResource(R.string.title_splash_screen)
            CounselUiState.InitialScreen -> stringResource(R.string.title_main_screen)
            CounselUiState.WriteScreen -> stringResource(R.string.title_write_screen)
            CounselUiState.LoadingScreen -> stringResource(R.string.title_loading_screen)
            is CounselUiState.ResultScreen -> stringResource(R.string.title_result_screen)
            is CounselUiState.WrongAskScreen -> stringResource(R.string.title_wrong_screen)
            is CounselUiState.ServerOutScreen -> stringResource(R.string.title_error_screen)
        },
        fontSize = dimensionResource(R.dimen.title_text_size).value.sp
    )
}