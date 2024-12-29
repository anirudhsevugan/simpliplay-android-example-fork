package com.ali.exoplayer

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.media3.exoplayer.ExoPlayer

@Composable
fun PlayerControls(player: ExoPlayer?) {
    val context = LocalContext.current
    val githubUrl = "https://github.com/akhorasani/Media3Player/" // Replace with your GitHub URL
    val githubUrl2 = "https://github.com/A-Star100/ExoPlayerCreator"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            // Open the GitHub repository in the default browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
            context.startActivity(intent)
        }) {
            Text("This Media3 Player Demo by @akhorasani on GitHub")
        }
        Button(onClick = {
            // Open the GitHub repository in the default browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl2))
            context.startActivity(intent)
        }) {
            Text("ExoPlayer Creator by @A-Star100 (Anirudh Sevugan) on GitHub")
        }
    }
}
