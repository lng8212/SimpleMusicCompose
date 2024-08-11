package com.longkd.simplemusiccompose.ui.music_bottom_bar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.longkd.simplemusiccompose.R
import com.longkd.simplemusiccompose.domain.model.Song
import com.longkd.simplemusiccompose.ui.player.PlayerUiState
import com.longkd.simplemusiccompose.util.FadeTransition
import com.longkd.simplemusiccompose.util.PlayerState
import com.longkd.simplemusiccompose.util.TransitionDurations
import com.longkd.simplemusiccompose.util.swipeable
import kotlin.math.absoluteValue

/**
 * @Author: longkd
 * @Since: 20:07 - 20/7/24
 */

@Composable
fun AnimatedNowPlayingBottomBar(
    musicControllerUiState: PlayerUiState,
    onEvent: (NowPlayingBottomBarUiEvent) -> Unit,
    onBarClick: () -> Unit,
) {
    val visible = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visible,
        enter = slideInVertically(
            animationSpec = nowPlayingBottomBarEnterAnimationSpec(),
            initialOffsetY = { it / 2 }
        ) + fadeIn(animationSpec = nowPlayingBottomBarEnterAnimationSpec()),
        exit = fadeOut()
    ) {
        NowPlayingBottomBar(musicControllerUiState, onEvent = onEvent, onBarClick = onBarClick)
    }
}

private fun <T> nowPlayingBottomBarEnterAnimationSpec() = TransitionDurations.Normal.asTween<T>(
    delayMillis = TransitionDurations.Fast.milliseconds,
)

@Composable
fun NowPlayingBottomBar(
    musicControllerUiState: PlayerUiState,
    onEvent: (NowPlayingBottomBarUiEvent) -> Unit,
    onBarClick: () -> Unit,
) {
    AnimatedContent(
        modifier = Modifier.fillMaxWidth(),
        contentKey = { it?.mediaId },
        targetState = musicControllerUiState.currentSong,
        transitionSpec = {
            val from = slideInVertically() + fadeIn()
            val to = slideOutVertically() + fadeOut()
            from togetherWith to
        },
        label = "c-now-playing-container"
    ) { song ->
        song?.let { currentSong ->
            Column {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.25f))
                        .height(2.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .background(MaterialTheme.colorScheme.surfaceTint)
                            .fillMaxHeight()
                            .fillMaxWidth(
                                if (musicControllerUiState.totalDuration != 0L) {
                                    (musicControllerUiState.currentPosition.toFloat() / musicControllerUiState.totalDuration)
                                } else 0F
                            )
                    )
                }
                ElevatedCard(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .swipeable(
                        onSwipeUp = {
                            onBarClick.invoke()
                        }
                    ),
                    shape = RectangleShape,
                    onClick = {
                        onBarClick.invoke()
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(0.dp, 8.dp)
                    ) {
                        Spacer(modifier = Modifier.width(12.dp))
                        AnimatedContent(
                            label = "c-now-playing-card-image",
                            targetState = currentSong,
                            contentKey = {
                                it.mediaId
                            },
                            transitionSpec = {
                                val from = fadeIn(
                                    animationSpec = TransitionDurations.Normal.asTween(
                                        delayMillis = TransitionDurations.Normal.milliseconds
                                    )
                                )
                                val to = fadeOut(TransitionDurations.Normal.asTween())
                                from togetherWith to
                            }
                        ) { song ->
                            AsyncImage(
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                model = ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(song.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        AnimatedContent(
                            label = "c-now-playing-card-content",
                            modifier = Modifier.weight(1f),
                            targetState = currentSong,
                            contentKey = {
                                it.mediaId
                            },
                            transitionSpec = {
                                val from = fadeIn(
                                    animationSpec = TransitionDurations.Normal.asTween(
                                        delayMillis = TransitionDurations.Normal.milliseconds
                                    )
                                )
                                val to = fadeOut(TransitionDurations.Normal.asTween())
                                from togetherWith to
                            }
                        ) { song ->
                            NowPlayingBottomBarContent(
                                song = song
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        IconButton(onClick = { onEvent.invoke(NowPlayingBottomBarUiEvent.OnPreviousMusic) }) {
                            Icon(painter = painterResource(id = R.drawable.ic_skip_previous), null)
                        }
                        IconButton(onClick = { onEvent.invoke(NowPlayingBottomBarUiEvent.OnPlayPauseMusic) }) {
                            Icon(
                                when (musicControllerUiState.playerState) {
                                    PlayerState.PLAYING -> painterResource(id = R.drawable.ic_pause)
                                    else -> painterResource(id = R.drawable.ic_play)
                                },
                                contentDescription =
                                null
                            )
                        }
                        IconButton(onClick = { onEvent.invoke(NowPlayingBottomBarUiEvent.OnNextMusic) }) {
                            Icon(
                                painterResource(id = R.drawable.ic_skip_next),
                                contentDescription = null
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }

    }
}

@Composable
private fun NowPlayingBottomBarContent(
    song: Song,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowPlayingBottomBarContentText(
            text = song.title,
            style = MaterialTheme.typography.bodyMedium
        )
        if (song.subtitle.isNotEmpty()) {
            NowPlayingBottomBarContentText(
                song.subtitle,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NowPlayingBottomBarContentText(text: String, style: TextStyle) {
    var showOverlay by remember {
        mutableStateOf(false)
    }
    Box {
        Text(
            text = text,
            style = style,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .basicMarquee(iterations = Int.MAX_VALUE)
                .onGloballyPositioned {
                    val offsetX = it.boundsInParent().centerLeft.x
                    showOverlay = offsetX.absoluteValue != 0f
                },
        )
        AnimatedVisibility(
            visible = showOverlay,
            modifier = Modifier.matchParentSize(),
            enter = FadeTransition.enterTransition(),
            exit = FadeTransition.exitTransition()
        ) {
            val backgroundColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
            Row {
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(backgroundColor, Color.Transparent)
                            )
                        )
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color.Transparent, backgroundColor)
                            )
                        )
                )
            }

        }
    }
}