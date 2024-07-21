package vku.duongdlt.winktraveller.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(
    list: List<String>,
    defaultText: String = "Select",
    color: Color = Color.Black,
    onSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    var selectedText by remember { mutableStateOf(defaultText) }

    ExposedDropdownMenuBox(
        modifier = Modifier
            .width(150.dp)
            .height(55.dp)
            .padding(top = 5.dp, start = 10.dp),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
//        Text(
//            text = selectedText,
//            modifier = Modifier
//                .menuAnchor()
//                .padding(8.dp)
//                .fillMaxWidth(),
//            color = color,
//            textAlign = TextAlign.Center
//        )
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            readOnly = true,
            trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
            modifier = Modifier.menuAnchor(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Yellow
            )
            )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(White)
                .padding(2.dp)
                .exposedDropdownSize()
        ) {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            color = color,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth())
                    },
                    onClick = {
                        selectedIndex = index
                        selectedText = item
                        expanded = false
                        onSelected(selectedIndex)
                    }
                )
            }
        }
    }
}