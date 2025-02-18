package org.comon.chillcounselor.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import org.comon.presentation.R

@Composable
fun CounselTextField(
    textValue: String,
    textLength: Int,
    changeTextValue: (String) -> Unit,
){
    OutlinedTextField(
        value = textValue,
        onValueChange = changeTextValue,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.text_field_horizontal_padding))
            .height(dimensionResource(R.dimen.text_field_height)),
        placeholder = {
            Text(stringResource(R.string.counsel_text_field_placeholder))
        },
        supportingText = {
            Text("$textLength / 1000")
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(alpha = 0.7f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.7f)
        )
    )
}