package org.mostafadevo.kmovies

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.mostafadevo.kmovies.presentation.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMovies",
    ) {
        App()
    }
}