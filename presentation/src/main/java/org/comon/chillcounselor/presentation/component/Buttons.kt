package org.comon.chillcounselor.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.google.android.material.color.MaterialColors
import org.comon.chillcounselor.presentation.ui.theme.ChillCounselorTheme
import org.comon.presentation.R

@Composable
fun BGMButton(
    bgmState: Boolean,
    toggleBGM: () -> Unit
){
    IconButton(
        onClick = toggleBGM
    ) {
        Icon(
            imageVector = if(bgmState){
                Icons.Default.Close
            } else {
                Icons.Filled.PlayArrow
            },
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
    requestCounsel: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
){
    Button(
        onClick = requestCounsel,
        modifier = modifier.padding(top = dimensionResource(R.dimen.button_top_padding)),
        enabled = enabled,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray,
        )
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
            .padding(top = dimensionResource(R.dimen.button_top_padding))
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