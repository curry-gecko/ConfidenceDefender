package io.github.currygecko.confidencekeeper.model

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.util.Log
import androidx.core.graphics.drawable.IconCompat
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppInformation(
    val packageName: String = "undefined package name",
    val packageLabel: String = "undefined package label"
) : Parcelable {
    fun getAppIcon(packageManager: PackageManager): Drawable {
        return try {
            packageManager.getApplicationIcon(this.packageName)
        } catch (e: Exception) {
            Log.e("AppIconError", "$this", e)
            ColorDrawable(android.graphics.Color.TRANSPARENT)
        }
    }

    fun makeIcon(context: Context): IconCompat {
        val drawable = getAppIcon(context.packageManager)
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return IconCompat.createWithBitmap(bitmap)

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

fun ApplicationInfo.getDummy(): ApplicationInfo = ApplicationInfo()