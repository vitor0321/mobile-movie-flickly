package com.walcker.flickly.cedarDS

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Book
import compose.icons.fontawesomeicons.solid.BookOpen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun CedarTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String? = null,
    icon: ImageVector? = null,
    iconContent: ImageVector? = null,
    containerColor: Color = MaterialTheme.colorScheme.background,
    onIconContentClick: () -> Unit = {},
    onIconContentLongClick: (() -> Unit)? = null,
    onNavigationBack: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Row {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                    description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
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
            iconContent?.let { icon ->
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp)
                        .combinedClickable(
                            interactionSource = noRippleInteraction,
                            indication = null,
                            hapticFeedbackEnabled = false,
                            onClick = { onIconContentClick() },
                            onLongClick = { (onIconContentLongClick ?: onIconContentClick)() }
                        ),
                    imageVector = icon,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        )
    )
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme(isDarkTheme = false) {
        CedarTopAppBar(
            title = "Bible Audio",
            description = "Peace be with you",
            icon = FontAwesomeIcons.Solid.ArrowLeft,
            iconContent = FontAwesomeIcons.Solid.BookOpen,
            onIconContentClick = { },
            onNavigationBack = { },
        )
    }
}