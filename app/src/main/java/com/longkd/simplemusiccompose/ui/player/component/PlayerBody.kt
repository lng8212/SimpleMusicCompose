package com.longkd.simplemusiccompose.ui.player.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.longkd.simplemusiccompose.R
import com.longkd.simplemusiccompose.domain.model.Song
import com.longkd.simplemusiccompose.ui.player.PlayerEvent
import com.longkd.simplemusiccompose.ui.player.PlayerUiState
import com.longkd.simplemusiccompose.util.PlayerState
import com.longkd.simplemusiccompose.util.toTime

/**
 * @Author: longkd
 * @Since: 16:56 - 11/8/24
 */

@Composable
fun PlayerBody(
    playerUiState: PlayerUiState,
    onNavigateUp: () -> Unit,
    onEvent: (PlayerEvent) -> Unit,
) {
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background
    val dominantColor by remember {
        mutableStateOf(Color.Transparent)
    }
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(playerUiState.currentSong?.imageUrl)
            .crossfade(true).build()
    )
    val iconResId =
        if (playerUiState.playerState == PlayerState.PLAYING) R.drawable.ic_pause else R.drawable.ic_play

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        PlayerScreenContent(
            song = playerUiState.currentSong ?: return,
            isSongPlaying = playerUiState.playerState == PlayerState.PLAYING,
            imagePainter = imagePainter,
            dominantColor = dominantColor,
            currentTime = playerUiState.currentPosition,
            totalTime = playerUiState.totalDuration,
            playPauseIcon = iconResId,
            playOrToggleSong = {
                onEvent(if (playerUiState.playerState == PlayerState.PLAYING) PlayerEvent.PauseSong else PlayerEvent.ResumeSong)
            },
            playNextSong = { onEvent(PlayerEvent.SkipToNextSong) },
            playPreviousSong = { onEvent(PlayerEvent.SkipToPreviousSong) },
            onSliderChange = { newPosition ->
                onEvent(PlayerEvent.SeekSongToPosition(newPosition.toLong()))
            },
            onForward = {
                onEvent(PlayerEvent.SeekSongToPosition(playerUiState.currentPosition + 10 * 1000))
            },
            onRewind = {
                playerUiState.currentPosition.let { currentPosition ->
                    onEvent(PlayerEvent.SeekSongToPosition(if (currentPosition - 10 * 1000 < 0) 0 else currentPosition - 10 * 1000))
                }
            },
            onClose = {
                onNavigateUp.invoke()
            }
        )
    }
}

@Composable
fun PlayerScreenContent(
    song: Song,
    isSongPlaying: Boolean,
    imagePainter: Painter,
    dominantColor: Color,
    currentTime: Long,
    totalTime: Long,
    @DrawableRes playPauseIcon: Int,
    playOrToggleSong: () -> Unit,
    playNextSong: () -> Unit,
    playPreviousSong: () -> Unit,
    onSliderChange: (Float) -> Unit,
    onRewind: () -> Unit,
    onForward: () -> Unit,
    onClose: () -> Unit,
) {
    val gradientColor = if (isSystemInDarkTheme()) {
        listOf(dominantColor, MaterialTheme.colorScheme.background)
    } else {
        listOf(MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.background)
    }

    val sliderColor = if (isSystemInDarkTheme()) {
        SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.onBackground,
            activeTrackColor = MaterialTheme.colorScheme.onBackground,
            inactiveTrackColor = MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.25f
            )
        )
    } else {
        SliderDefaults.colors(
            thumbColor = dominantColor,
            activeTrackColor = dominantColor,
            inactiveTrackColor = dominantColor.copy(
                alpha = 0.25f
            )
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface {
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = gradientColor,
                            endY = LocalConfiguration.current.screenHeightDp.toFloat() + LocalDensity.current.density
                        )
                    )
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                Column {
                    IconButton(onClick = { onClose.invoke() }) {
                        Image(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(LocalContentColor.current)
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 32.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .weight(1f, fill = false)
                            .aspectRatio(1f)
                    ) {
                        AnimatedVinyl(painter = imagePainter, isSongPlaying = isSongPlaying)
                    }
                    Text(
                        text = song.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(song.subtitle,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.graphicsLayer {
                            alpha = 0.60f
                        })

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    ) {

                        Slider(
                            value = currentTime.toFloat(),
                            modifier = Modifier.fillMaxWidth(),
                            valueRange = 0f..totalTime.toFloat(),
                            colors = sliderColor,
                            onValueChange = {
                                onSliderChange.invoke(it)
                            },
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                currentTime.toTime(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                totalTime.toTime(), style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_skip_previous),
                            contentDescription = "Skip Previous",
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable(onClick = {
                                    playPreviousSong.invoke()
                                })
                                .padding(12.dp)
                                .size(32.dp)
                        )
                        Icon(
                            painterResource(id = R.drawable.ic_replay),
                            contentDescription = "Replay 10 seconds",
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable(onClick = onRewind)
                                .padding(12.dp)
                                .size(32.dp)
                        )
                        Icon(
                            painter = painterResource(playPauseIcon),
                            contentDescription = "Play",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.onBackground)
                                .clickable(onClick = {
                                    playOrToggleSong.invoke()
                                })
                                .size(64.dp)
                                .padding(8.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_forward),
                            contentDescription = "Forward 10 seconds",
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable(onClick = onForward)
                                .padding(12.dp)
                                .size(32.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_skip_next),
                            contentDescription = "Skip Next",
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable(onClick = {
                                    playNextSong.invoke()
                                })
                                .padding(12.dp)
                                .size(32.dp)
                        )
                    }
                }
            }
        }
    }
}