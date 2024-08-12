package com.linkdev.finalproject.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkdev.finalproject.R
import com.linkdev.finalproject.viewModel.CameraViewModel

@Composable
fun CameraScreen(viewModel: CameraViewModel = hiltViewModel()) {
    val imageURIs by viewModel.imageURIs.observeAsState(listOf())
    val context = LocalContext.current

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isCaptured ->
            if (isCaptured) {
                viewModel.tempUri.value?.let {
                    viewModel.addImageUri(it)
                }
            }
        }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                viewModel.createTempUri()
                viewModel.tempUri.value?.let {
                    cameraLauncher.launch(it)
                }
            } else {
                Toast.makeText(context, "Permission is not granted", Toast.LENGTH_LONG).show()
            }
        }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                val isPermissionGranted = ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.CAMERA
                ) == android.content.pm.PackageManager.PERMISSION_GRANTED

                if (isPermissionGranted) {
                    viewModel.createTempUri()
                    viewModel.tempUri.value?.let {
                        cameraLauncher.launch(it)
                    }
                } else {
                    cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_alt_24),
                    contentDescription = "Camera Icon",
                    tint = Color.Red
                )
            }
        }

        LazyColumn {
            itemsIndexed(imageURIs) { _, imageUri ->
                AnimatedVisibility(visible = imageUri != Uri.EMPTY) {
                    Image(bitmap = createBitmap(imageUri, context).asImageBitmap(), contentDescription = "Captured Image")
                }
            }
        }

        if (imageURIs.isEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "No images yet",
                fontSize = 16.sp
            )
        }
    }
}

fun createBitmap(imageUri: Uri, context: Context): Bitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, imageUri))
    } else {
        MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
    }
}
