package com.parimka.machas.com.Utilities2ki78

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Environment
import android.util.Base64
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun Context.checkPermissions2ki78 () {
    Dexter.withContext(this)
        .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {

            }
        }).check()
}

fun Context.createTempFile2ki78 (): File {
    val date2ki78 = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        .format(Date())
    val fileDir2ki78 = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("TMP${date2ki78}_2ki78", ".jpg", fileDir2ki78)
}

fun String.decodeStrings2ki78 () = String(Base64.decode(this, Base64.DEFAULT))

fun Context.internetChecker2ki78 (): Boolean {
    val connectivityManager2ki78 = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val networkCapabilities2ki78 = connectivityManager2ki78.getNetworkCapabilities(connectivityManager2ki78.activeNetwork) ?: return false
        return networkCapabilities2ki78.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        for (network2ki78 in connectivityManager2ki78.allNetworks) {
            connectivityManager2ki78.getNetworkInfo(network2ki78)?.let {
                if (it.isConnected) return true
            }
        }
        return false
    }
}