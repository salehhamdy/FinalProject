package com.linkdev.finalproject.screens

import android.Manifest
import android.content.ContentValues
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.linkdev.finalproject.viewmodel.CameraViewModel
import kotlinx.coroutines.launch


@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val imageUri by viewModel.imageUri.observeAsState()

    // Create a launcher for the camera intent
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (!success) {
                Toast.makeText(context, "Failed to take picture", Toast.LENGTH_SHORT).show()
            }
        }
    )

    // Create a launcher to request camera permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                // Generate a URI for the new image
                val uri = context.contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    ContentValues()
                )
                if (uri != null) {
                    viewModel.setImageUri(uri)
                    cameraLauncher.launch(uri)
                } else {
                    Toast.makeText(context, "Failed to create image file", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }) {
            Text("Open Camera")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (imageUri != null) {
            Image(
                painter = rememberImagePainter(data = imageUri),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .border(2.dp, Color.Black),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(text = "No Image Selected")
        }
    }
}