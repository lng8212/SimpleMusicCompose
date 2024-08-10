package com.longkd.simplemusiccompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.longkd.simplemusiccompose.SharedViewModel
import com.longkd.simplemusiccompose.ui.bottom_bar.AnimatedNowPlayingBottomBar
import com.longkd.simplemusiccompose.ui.home.HOME_ROUTE
import com.longkd.simplemusiccompose.ui.home.homeScreen
import com.longkd.simplemusiccompose.ui.player.PLAYER_ROUTE
import com.longkd.simplemusiccompose.ui.player.playerScreen
import com.longkd.simplemusiccompose.ui.profile.profileScreen
import com.longkd.simplemusiccompose.ui.search.searchScreen

/**
 * @Author: longkd
 * @Since: 19:03 - 20/7/24
 */

@Composable
fun App(sharedViewModel: SharedViewModel) {
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    val musicControllerUiState = sharedViewModel.nowPlayingBottomBarUiState
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    showBottomBar = navBackStackEntry?.destination?.route != PLAYER_ROUTE
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                Column {
                    AnimatedNowPlayingBottomBar(musicControllerUiState = musicControllerUiState) {
                        sharedViewModel.onEvent(it)
                    }
//                    NavigationBar {
//                        BottomNavigationItem().bottomNavigationItems()
//                            .forEachIndexed { index, item ->
//                                NavigationBarItem(
//                                    selected = index == navigationSelectedItem,
//                                    onClick = {
//                                        navigationSelectedItem = index
//                                        navController.navigate(item.route) {
//                                            popUpTo(navController.graph.findStartDestination().id) {
//                                                saveState = true
//                                            }
//                                            launchSingleTop = true
//                                            restoreState = true
//                                        }
//                                    },
//                                    icon = {
//                                        Icon(
//                                            imageVector = item.icon,
//                                            contentDescription = item.label
//                                        )
//                                    },
//                                    label = {
//                                        Text(text = item.label)
//                                    })
//                            }
//                    }
                }
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            NavHost(navController = navController, HOME_ROUTE) {
                homeScreen()
                playerScreen()
                profileScreen()
                searchScreen()
                playerScreen()
            }
        }
    }
}