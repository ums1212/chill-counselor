package org.comon.chillcounselor.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.comon.presentation.R

@Composable
fun ChillGuyImage(size:Dp){
    Image(
        painter = painterResource(R.drawable.chillguy),
        contentDescription = stringResource(R.string.content_description_splash_screen_image),
        modifier = Modifier
            .size(size)
            .padding(top = 24.dp)
    )
}