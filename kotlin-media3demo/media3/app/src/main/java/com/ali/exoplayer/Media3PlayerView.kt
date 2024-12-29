package com.ali.exoplayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun Media3PlayerView(videoUrl: String, playerViewModel: PlayerViewModel = viewModel()) {

    val context = LocalContext.current
    val player by playerViewModel.playerState.collectAsState()

    LaunchedEffect(videoUrl) {
        playerViewModel.initializePlayer(context, videoUrl)
    }

    DisposableEffect(Unit) {
        onDispose {
            playerViewModel.savePlayerState()
            playerViewModel.releasePlayer()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Media3AndroidView will take the remaining available space
        Media3AndroidView(player)  // Use weight to take the rest of the space

        // Player controls will take only the space it needs
        PlayerControls(player)
    }
}

@Composable
fun Media3AndroidView(player: ExoPlayer?, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth(), // Ensure PlayerView takes full width
        factory = { context ->
            PlayerView(context).apply {
                this.player = player
            }
        },
        update = { playerView ->
            playerView.player = player
        }
    )
}
