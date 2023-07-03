package io.github.currygecko.confidencekeeper.ui.compose.list

import io.github.currygecko.confidencekeeper.model.AppInformation

interface ClickItemViewListener {
    fun onClick()
    fun onLongClick()
    fun onSettingsButtonClick(packageName: String)
    fun onMakeShortcutButtonClick(appInformation: AppInformation)
}
