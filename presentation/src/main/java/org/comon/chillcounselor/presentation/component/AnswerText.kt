package org.comon.chillcounselor.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import org.comon.presentation.R

@Composable
fun AnswerText(text: String){
    Card(
        modifier = Modifier
            .width(dimensionResource(R.dimen.card_min_width))
            .padding(top = dimensionResource(R.dimen.answer_text_card_top_padding))
            .padding(horizontal = dimensionResource(R.dimen.answer_text_card_horizontal_padding)),
        border = BorderStroke(
            width = dimensionResource(R.dimen.answer_text_card_border_stroke),
            color = Color.Blue
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(dimensionResource(R.dimen.answer_text_card_text_padding)),
            fontSize = dimensionResource(R.dimen.description_text_size).value.sp,
        )
    }
}