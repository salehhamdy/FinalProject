package com.linkdev.finalproject.screens.camerascreen

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.linkdev.finalproject.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class CameraRepository @Inject constructor(@ApplicationContext private val context: Context) {

    @SuppressLint("SimpleDateFormat")
    fun createImageFile(): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "LINK_${timeStamp}_"
        val image = File.createTempFile(
            imageFileName,
            ".jpg",
            context.externalCacheDir
        )
        return FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".provider",
            image
        )
    }
}
