package vku.duongdlt.winktraveller.component

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableWithoutIndication(
    onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() }
    )
}
