package vku.duongdlt.winktraveller.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.asImageBitmap
import com.seiko.imageloader.model.ImageEvent
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.model.ImageResult
import com.seiko.imageloader.rememberImageAction
import com.seiko.imageloader.rememberImageActionPainter
import vku.duongdlt.winktraveller.component.LoadingDialog
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme

val BOTTOM_NAV_SPACE = 90.dp
@Composable
fun ImageItem(
    data: Any,
    playAnime: Boolean = true,
    corner: Dp = 10.dp,
    modifier: Modifier = Modifier,
) {
    var isLoading by remember { mutableStateOf(true) }

    Box(contentAlignment = Alignment.Center) {
        val brightness = -50f
        val colorMatrix = floatArrayOf(
            1f, 0f, 0f, 0f, brightness,
            0f, 1f, 0f, 0f, brightness,
            0f, 0f, 1f, 0f, brightness,
            0f, 0f, 0f, 1f, 0f
        )

        val request = remember(data, playAnime) {
            ImageRequest {
                data(data)
                options {
                    playAnimate = playAnime
                }
            }
        }

        val action by rememberImageAction(request)
        val painter = rememberImageActionPainter(action)

        when (val current = action) {
            is ImageEvent.StartWithDisk,
            is ImageEvent.StartWithFetch,
            is ImageEvent.StartWithMemory -> {
                isLoading = true
            }
            is ImageEvent.Progress -> {
                // Show progress indicator if needed
            }
            is ImageResult.Bitmap -> {
                isLoading = false
                Image(
                    bitmap = current.bitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(corner)),
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
                )
            }
            is ImageResult.Error -> {
                isLoading = false
                // Show error message if needed
            }
            else -> {
                isLoading = false
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = HeliaTheme.colors.primary500
            )
        }
    }
}



@Composable
fun AnimateVisibility(
    visible: Boolean = true,
    modifier: Modifier = Modifier,

    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(animationSpec = tween(1000)) +
                expandVertically(
                    animationSpec = tween(1500)
                ),
        exit = fadeOut(animationSpec = tween(1000)) +
                shrinkVertically(
                    animationSpec = tween(1500)
                )
    ) {
        content.invoke()
    }
}