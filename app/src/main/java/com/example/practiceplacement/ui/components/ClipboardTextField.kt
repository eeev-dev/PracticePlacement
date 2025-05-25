package com.example.practiceplacement.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import com.example.practiceplacement.R

@Composable
fun ClipboardTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    val clipboardManager = LocalClipboardManager.current

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) }, // важно: вызываем callback!
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = {
                val textFromClipboard = clipboardManager.getText()?.text
                if (textFromClipboard != null) {
                    onValueChange(textFromClipboard)
                }
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.clipboard),
                    contentDescription = "Вставить из буфера"
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.blue),
            unfocusedBorderColor = colorResource(R.color.inactive_tab),
            focusedLabelColor = colorResource(R.color.blue),
            unfocusedLabelColor = colorResource(R.color.inactive_tab),
            cursorColor = colorResource(R.color.blue),
            unfocusedTrailingIconColor = colorResource(R.color.inactive_tab),
            focusedTrailingIconColor = colorResource(R.color.blue)
        )
    )
}