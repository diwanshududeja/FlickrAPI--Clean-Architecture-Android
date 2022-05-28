package com.demo.flickr.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    textInput: String,
    hint: String = "",
    onTextInputChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        value = textState.value,
        onValueChange = {
            textState.value = it
            onTextInputChange(it.text)
                        },
        placeholder = {
            Text(hint)
        },
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick()
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
        )
    )
}