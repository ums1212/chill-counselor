package org.comon.chillcounselor.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.comon.chillcounselor.presentation.component.ChillGuyImage
import org.comon.chillcounselor.presentation.component.TitleText
import org.comon.presentation.R

@Preview
@Composable
fun SplashScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
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
    }
}