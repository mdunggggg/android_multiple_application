package com.mdunggggg.jamendo_music.screen.ext

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Int.toHeight() = Spacer(modifier = Modifier.height(this.dp))

@Composable
fun Int.toWidth() = Spacer(modifier = Modifier.width(this.dp))