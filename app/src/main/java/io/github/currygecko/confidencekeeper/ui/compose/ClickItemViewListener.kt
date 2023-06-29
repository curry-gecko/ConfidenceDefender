package io.github.currygecko.confidencekeeper.ui.compose

class ClickItemViewListener(
    private val onClickListener: (() -> Unit)?,
    private val onLongClickListener: (() -> Unit)?
) {
    fun onClick() {
        onClickListener?.invoke()
    }

    fun onLongClick() {
        onLongClickListener?.invoke()
    }
}

