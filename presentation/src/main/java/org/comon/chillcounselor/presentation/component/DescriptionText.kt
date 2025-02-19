package org.comon.chillcounselor.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import org.comon.presentation.R

@Composable
fun DescriptionText(text: String){
    Text(
        text = text,
        fontSize = dimensionResource(R.dimen.description_text_size).value.sp
    )
}