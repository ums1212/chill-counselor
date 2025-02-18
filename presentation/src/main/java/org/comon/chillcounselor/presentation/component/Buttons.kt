package org.comon.chillcounselor.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import org.comon.presentation.R

@Composable
fun BGMButton(toggleBGM: () -> Unit){
    IconButton(
        onClick = toggleBGM
    ) {
        Icon(
            Icons.Filled.PlayArrow,
            stringResource(R.string.content_description_bgm_button)
        )
    }
}

@Composable
fun StartButton(
    startWrite: ()->Unit,
    modifier: Modifier = Modifier,
){
    Button(
        onClick = startWrite,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.button_horizontal_padding))
    ) {
        Text(
            text = stringResource(R.string.start_button),
            fontSize = dimensionResource(R.dimen.button_text_size).value.sp
        )
    }
}

@Composable
fun CompleteButton(
    requestCounsel: ()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = requestCounsel,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.complete_button),
            fontSize = dimensionResource(R.dimen.button_text_size).value.sp
        )
    }
}

@Composable
fun CancelButton(
    cancelRequest: ()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = cancelRequest,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.button_horizontal_padding))
    ) {
        Text(
            text = stringResource(R.string.cancel_button),
            fontSize = dimensionResource(R.dimen.button_text_size).value.sp
        )
    }
}

@Composable
fun WriteNewButton(
    startWrite: ()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = startWrite,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.button_horizontal_padding))
    ) {
        Text(
            text = stringResource(R.string.write_new_button),
            fontSize = dimensionResource(R.dimen.button_text_size).value.sp
        )
    }
}

@Composable
fun BackButton(
    backToInitScreen: ()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = backToInitScreen,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.button_horizontal_padding))
    ) {
        Text(
            text = stringResource(R.string.back_button),
            fontSize = dimensionResource(R.dimen.button_text_size).value.sp
        )
    }
}

@Composable
fun RewriteButton(
    startWrite: ()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = startWrite,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.button_horizontal_padding))
    ) {
        Text(
            text = stringResource(R.string.rewrite_button),
            fontSize = dimensionResource(R.dimen.button_text_size).value.sp
        )
    }
}

@Composable
fun FinishButton(
    finishApp: ()->Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = finishApp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.button_horizontal_padding)),
        colors = ButtonColors(
            containerColor = Color.Red,
            contentColor = Color.White,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        )
    ) {
        Text(
            text = stringResource(R.string.quit_button),
            fontSize = dimensionResource(R.dimen.button_text_size).value.sp
        )
    }
}