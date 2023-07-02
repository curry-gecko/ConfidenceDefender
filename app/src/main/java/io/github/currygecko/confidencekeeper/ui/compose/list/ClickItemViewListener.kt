package io.github.currygecko.confidencekeeper.ui.compose.list

interface ClickItemViewListener {
    fun onClick()
    fun onLongClick()
    fun onSettingsButtonClick(packageName: String)  // Settings Button click event
    fun onMakeShortcutButtonClick(packageName: String)  // MakeShortcut Button click event
}
