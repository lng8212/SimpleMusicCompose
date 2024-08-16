package com.longkd.simplemusiccompose.ui.setting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * @Author: longkd
 * @Since: 19:27 - 20/7/24
 */

const val SETTING_ROUTE = "setting_route"

fun NavGraphBuilder.settingScreen() {
    composable(route = SETTING_ROUTE) {
        SettingRoute()
    }
}