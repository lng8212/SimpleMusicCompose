package com.longkd.simplemusiccompose.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.longkd.simplemusiccompose.util.TransitionDurations
import com.longkd.simplemusiccompose.util.swipeable

/**
 * @Author: longkd
 * @Since: 20:07 - 20/7/24
 */

@Composable
fun AnimatedNowPlayingBottomBar(insetPadding: Boolean = false) {
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

    }
}

private fun <T> nowPlayingBottomBarEnterAnimationSpec() = TransitionDurations.Normal.asTween<T>(
    delayMillis = TransitionDurations.Fast.milliseconds,
)

@Composable
fun NowPlayingBottomBar(
    modifier: Modifier = Modifier,
    insetPadding: Boolean = true,
    onClickBottomBar: () -> Unit,
) {
    AnimatedContent(
        modifier = Modifier.fillMaxWidth(),
        contentKey = { false },
        targetState = null,
        transitionSpec = {
            val from = slideInVertically() + fadeIn()
            val to = slideOutVertically() + fadeOut()
            from togetherWith to
        }, label = ""
    ) { currentTarget ->
        currentTarget?.let {

        }
        Column {
            ElevatedCard(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .swipeable(
                    onSwipeUp = {
                        onClickBottomBar.invoke()
                    },
                    onSwipeDown = {

                    }
                ),
                shape = RectangleShape,
                onClick = {
                    onClickBottomBar.invoke()
                }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp, 8.dp)
                ) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
                if (insetPadding) {
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            }
        }

    }
}