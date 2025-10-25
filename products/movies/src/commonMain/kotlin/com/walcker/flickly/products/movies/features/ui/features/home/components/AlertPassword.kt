package com.walcker.flickly.products.movies.features.ui.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesInternalRoute
import com.walcker.flickly.products.movies.strings.MoviesListStrings
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Eye
import compose.icons.fontawesomeicons.solid.EyeSlash

@Composable
internal fun AlertPassword(
    strings: MoviesListStrings,
    showPasswordDialog: (Boolean) -> Unit,
    onEvent: (HomeMoviesInternalRoute) -> Unit
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = { showPasswordDialog(false) },
        title = { Text(text = strings.passwordDialogTitle) },
        text = {
            Column {
                OutlinedTextField(
                    value = password,
                    onValueChange = { input ->
                        val digits = input.filter(Char::isDigit).take(10)
                        password = digits
                    },
                    label = { Text(strings.passwordFieldLabel) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    maxLines = 1,
                    textStyle = TextStyle(fontSize = 26.sp),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                imageVector = if (passwordVisible) FontAwesomeIcons.Solid.EyeSlash else FontAwesomeIcons.Solid.Eye,
                                contentDescription = null
                            )
                        }
                    },
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onEvent(HomeMoviesInternalRoute.OnGoToAudio(password = password))
                    showPasswordDialog(false)
                    password = ""
                    passwordVisible = false
                },
                enabled = password.length >= 7
            ) { Text(strings.passwordConfirmLabel) }
        },
        dismissButton = {
            Button(onClick = {
                showPasswordDialog(false)
                password = ""
                passwordVisible = false
            }) { Text(strings.passwordDismissLabel) }
        }
    )
}
