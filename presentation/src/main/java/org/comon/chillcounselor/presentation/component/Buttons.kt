package org.comon.chillcounselor.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    modifier: Modifier = Modifier,
    startWrite: ()->Unit,
){
    Button(
        modifier = modifier,
        onClick = startWrite
    ) {
        Text(stringResource(R.string.start_button))
    }
}

@Composable
fun CompleteButton(
    modifier: Modifier = Modifier,
    requestCounsel: ()->Unit
){
    Button(
        onClick = requestCounsel,
        modifier = modifier
    ) {
        Text(stringResource(R.string.complete_button))
    }
}

@Composable
fun CancelButton(
    modifier: Modifier = Modifier,
    cancelRequest: ()->Unit
){
    Button(
        onClick = cancelRequest,
        modifier = modifier
    ) {
        Text(stringResource(R.string.cancel_button))
    }
}

@Composable
fun WriteNewButton(
    modifier: Modifier = Modifier,
    backToInitScreen: ()->Unit
){
    Button(
        onClick = backToInitScreen,
        modifier = modifier
    ) {
        Text(stringResource(R.string.write_new_button))
    }
}

@Composable
fun RewriteButton(
    modifier: Modifier = Modifier,
    startWrite: ()->Unit
){
    Button(
        onClick = startWrite,
        modifier = modifier
    ) {
        Text(stringResource(R.string.rewrite_button))
    }
}

@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    finishApp: ()->Unit
){
    Button(
        onClick = finishApp,
        modifier = modifier
    ) {
        Text(stringResource(R.string.rewrite_button))
    }
}