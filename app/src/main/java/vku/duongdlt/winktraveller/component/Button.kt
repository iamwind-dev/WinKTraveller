package vku.duongdlt.winktraveller.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import vku.duongdlt.winktraveller.R


@Composable
fun PrimaryButton(title: String, paddingValues: PaddingValues = PaddingValues(),onClick: () -> Unit){
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(13,110,253))
        ,shape = RoundedCornerShape(16.dp),modifier = Modifier
            .padding(16.dp)
            .width(500.dp)
            .height(70.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = title,
            color = colorResource(R.color.white),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
fun TripitacaPrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    contentPadding: PaddingValues = PaddingValues(top = 13.dp, bottom = 14.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        colors = colors,
        elevation = null,
        shape = shape
    ) {
        Text(text = text)
    }
}