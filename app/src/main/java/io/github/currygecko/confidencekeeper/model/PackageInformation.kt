package io.github.currygecko.confidencekeeper.model

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

data class AppInformation(
    val packageName: String,
    val packageLabel: String,
    val appIcon: Drawable
) {
}

fun ApplicationInfo.asAppInfo(manager: PackageManager): AppInformation {
    return AppInformation(
        packageName = this.packageName.toString(),
        packageLabel = this.loadLabel(manager).toString(),
        appIcon = manager.getApplicationIcon(this.packageName.toString())
    )
}