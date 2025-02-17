package org.comon.chillcounselor.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(title:String){
    Text(
        text = title,
        fontSize = 24.sp
    )
}