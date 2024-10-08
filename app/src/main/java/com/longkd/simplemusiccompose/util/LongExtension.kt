package com.longkd.simplemusiccompose.util

import android.annotation.SuppressLint

/**
 * @Author: longkd
 * @Since: 17:43 - 11/8/24
 */
@SuppressLint("DefaultLocale")
fun Long.toTime(): String {
    val stringBuffer = StringBuffer()

    val minutes = (this / 60000).toInt()
    val seconds = (this % 60000 / 1000).toInt()

    stringBuffer
        .append(String.format("%02d", minutes))
        .append(":")
        .append(String.format("%02d", seconds))

    return stringBuffer.toString()
}