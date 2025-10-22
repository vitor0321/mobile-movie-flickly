package com.walcker.flickly.cedarDS

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun CedarTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    icon: ImageVector? = null,
    iconAudio: ImageVector? = null,
    containerColor: Color = MaterialTheme.colorScheme.background,
    onAudio: () -> Unit = {},
    onNavigationBack: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold

            )
        },
        navigationIcon = {
            Box(
                modifier = Modifier.padding(12.dp)
            ) {
                icon?.let { imageVector ->
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { onNavigationBack() },
                        content = {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = imageVector,
                                contentDescription = ""
                            )
                        }
                    )
                }
            }
        },
        actions = {
            val noRippleInteraction = remember { MutableInteractionSource() }
            iconAudio?.let { icon ->
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp)
                        .combinedClickable(
                            interactionSource = noRippleInteraction,
                            indication = null,
                            hapticFeedbackEnabled = false,
                            onClick = { },
                            onLongClick = { onAudio() }
                        ),
                    imageVector = icon,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor
        )
    )
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme(isDarkTheme = false) {
        CedarTopAppBar(
            title = "Movie Detail",
            icon = FontAwesomeIcons.Solid.ArrowLeft,
            onAudio = { },
            onNavigationBack = { },
        )
    }
}