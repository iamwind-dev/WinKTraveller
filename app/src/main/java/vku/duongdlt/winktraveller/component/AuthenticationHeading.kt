package vku.duongdlt.winktraveller.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import vku.duongdlt.winktraveller.ui.theme.HeliaTheme


@Composable
fun AuthenticationHeading(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(
        modifier = modifier,
        text = text,
        style = HeliaTheme.typography.heading3,
        color = if (HeliaTheme.theme.isDark) HeliaTheme.colors.white else HeliaTheme.colors.greyscale900,
        textAlign = textAlign
    )
}