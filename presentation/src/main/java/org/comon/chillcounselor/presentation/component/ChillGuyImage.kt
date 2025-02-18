package org.comon.chillcounselor.presentation.component

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.comon.chillcounselor.presentation.viewmodel.ChillGuyState
import org.comon.presentation.R

@Composable
fun ChillGuyImage(chillGuyState: ChillGuyState){

    val transition = updateTransition(
        targetState = chillGuyState,
        label = stringResource(R.string.update_transition_label)
    )

    val imageSize by transition.animateDp(
        transitionSpec = {
            tween(durationMillis = integerResource(R.integer.image_transition_duration_mills))
        },
        label = stringResource(R.string.animate_dp_label)
    ) { state ->
        when(state){
            ChillGuyState.SmallGuy -> dimensionResource(R.dimen.chill_guy_small)
            else -> dimensionResource(R.dimen.chill_guy_large)
        }
    }

    if(chillGuyState == ChillGuyState.ErrorGuy){
        Image(
            painter = painterResource(R.drawable.emptyhouse),
            contentDescription = stringResource(R.string.content_description_error_image),
            modifier = Modifier
                .size(imageSize)
                .padding(top = dimensionResource(R.dimen.chill_guy_padding_top))
        )
    } else {
        Image(
            painter = painterResource(R.drawable.chillguy),
            contentDescription = stringResource(R.string.content_description_chill_guy_image),
            modifier = Modifier
                .size(imageSize)
                .padding(top = dimensionResource(R.dimen.chill_guy_padding_top))
        )
    }

}