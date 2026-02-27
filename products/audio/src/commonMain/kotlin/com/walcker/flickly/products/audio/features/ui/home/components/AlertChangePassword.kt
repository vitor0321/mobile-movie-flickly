package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walcker.flickly.core.domain.setting.model.PasswordSettingsHolder.MAX_SIZE_PASSWORD
import com.walcker.flickly.core.domain.setting.model.PasswordSettingsHolder.MIN_SIZE_PASSWORD
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.audio.features.ui.home.HomeAudioInternalRoute
import com.walcker.flickly.products.audio.strings.HomeAudioStrings
import com.walcker.flickly.products.audio.strings.homeAudioStringsPt
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Eye
import compose.icons.fontawesomeicons.solid.EyeSlash

@Composable
internal fun AlertChangePassword(
    strings: HomeAudioStrings,
    onDismiss: () -> Unit,
    onEvent: (HomeAudioInternalRoute) -> Unit,
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var mismatchError by remember { mutableStateOf(false) }

    val isLengthValid = newPassword.length in MIN_SIZE_PASSWORD..MAX_SIZE_PASSWORD

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = strings.changePasswordDialogTitle) },
        text = {
            Column {
                Text(
                    text = strings.changePasswordMinMaxError,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { input ->
                        newPassword = input.filter(Char::isDigit).take(MAX_SIZE_PASSWORD)
                        mismatchError = false
                    },
                    label = { Text(strings.changePasswordNewPasswordHint) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    maxLines = 1,
                    textStyle = TextStyle(fontSize = 26.sp),
                    isError = newPassword.isNotEmpty() && !isLengthValid,
                    trailingIcon = {
                        IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                imageVector = if (newPasswordVisible) FontAwesomeIcons.Solid.EyeSlash else FontAwesomeIcons.Solid.Eye,
                                contentDescription = null
                            )
                        }
                    },
                    supportingText = if (newPassword.isNotEmpty() && !isLengthValid) {
                        { Text(strings.changePasswordMinMaxError) }
                    } else null,
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { input ->
                        confirmPassword = input.filter(Char::isDigit).take(MAX_SIZE_PASSWORD)
                        mismatchError = false
                    },
                    label = { Text(strings.changePasswordNewPasswordHint) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    maxLines = 1,
                    textStyle = TextStyle(fontSize = 26.sp),
                    isError = mismatchError,
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                imageVector = if (confirmPasswordVisible) FontAwesomeIcons.Solid.EyeSlash else FontAwesomeIcons.Solid.Eye,
                                contentDescription = null
                            )
                        }
                    },
                    supportingText = if (mismatchError) {
                        {
                            Text(
                                text = strings.changePasswordMismatchError,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    } else if (confirmPassword.isNotEmpty() && confirmPassword.length !in MIN_SIZE_PASSWORD..MAX_SIZE_PASSWORD) {
                        {
                            Text(
                                text = strings.changePasswordMinMaxError,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    } else null,
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (newPassword == confirmPassword) {
                        onEvent(HomeAudioInternalRoute.OnChangePassword(newPassword = newPassword))
                        onDismiss()
                    } else {
                        mismatchError = true
                    }
                },
                enabled = isLengthValid && confirmPassword.length in MIN_SIZE_PASSWORD..MAX_SIZE_PASSWORD
            ) { Text(strings.changePasswordConfirmButton) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(strings.changePasswordCancelButton)
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun AlertChangePasswordPreview() {
    MoviesAppTheme {
        AlertChangePassword(
            strings = homeAudioStringsPt,
            onDismiss = {},
            onEvent = {},
        )
    }
}