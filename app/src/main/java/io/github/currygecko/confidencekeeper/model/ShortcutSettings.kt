package io.github.currygecko.confidencekeeper.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShortcutSettings(
    val volume: Int
) : Parcelable
