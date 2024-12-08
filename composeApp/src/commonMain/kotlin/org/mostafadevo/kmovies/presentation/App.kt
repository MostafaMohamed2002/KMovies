package org.mostafadevo.kmovies.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.jetbrains.compose.reload.DevelopmentEntryPoint
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mostafadevo.kmovies.presentation.theme.AppTheme

@Composable
@Preview
fun App() {
    DevelopmentEntryPoint {
        AppTheme(
            darkTheme = true,
        ) {
            Scaffold {

            }
        }
    }
}

