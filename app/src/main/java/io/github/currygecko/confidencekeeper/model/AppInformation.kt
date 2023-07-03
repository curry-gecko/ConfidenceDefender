package io.github.currygecko.confidencekeeper.model

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppInformation(
    val packageName: String,
    val packageLabel: String
) : Parcelable {
    fun getAppIcon(packageManager: PackageManager): Drawable {
        return packageManager.getApplicationIcon(this.packageName)
    }

    companion object {
        const val EXTRA_KEY: String = "AppInformation"
    }
}

fun ApplicationInfo.asAppInfo(manager: PackageManager): AppInformation {
    return AppInformation(
        packageName = this.packageName.toString(),
        packageLabel = this.loadLabel(manager).toString()
    )
}
