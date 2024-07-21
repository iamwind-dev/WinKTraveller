package vku.duongdlt.winktraveller.component

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme

@Composable
fun BackgroundSurface(
    modifier: Modifier = Modifier,
    color: Color = HeliaTheme.backgroundColor,
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier, color = color, content = content)
}