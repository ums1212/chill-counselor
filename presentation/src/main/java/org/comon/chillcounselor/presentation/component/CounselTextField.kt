package org.comon.chillcounselor.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import org.comon.presentation.R

@Composable
fun CounselTextField(
    textValue: String,
    changeTextValue: (String) -> Unit,
){
    OutlinedTextField(
        value = textValue,
        onValueChange = changeTextValue,
        modifier = Modifier
            .width(dimensionResource(R.dimen.button_min_width))
            .padding(horizontal = dimensionResource(R.dimen.text_field_horizontal_padding))
            .height(dimensionResource(R.dimen.text_field_height)),
        placeholder = {
            Text(
                text = stringResource(R.string.counsel_text_field_placeholder),
                overflow = TextOverflow.Visible
            )
        },
        supportingText = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.counsel_text_field_counter, textValue.length))
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(alpha = 0.7f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.7f),
            errorContainerColor = Color.White.copy(alpha = 0.7f),
        )
    )
}