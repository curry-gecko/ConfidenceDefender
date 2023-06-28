package io.github.currygecko.confidencekeeper.model

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

data class AppInfo(
    val packageName: String,
    val packageLabel: String
) {
}

fun ApplicationInfo.asAppInfo(manager: PackageManager): AppInfo {
    return AppInfo(
        packageName = this.packageName.toString(),
        packageLabel = this.loadLabel(manager).toString()
    )
}