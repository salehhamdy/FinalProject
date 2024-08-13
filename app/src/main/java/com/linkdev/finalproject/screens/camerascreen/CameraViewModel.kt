package com.linkdev.finalproject.screens.camerascreen

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val cameraRepository: CameraRepository) : ViewModel() {

    private val _imageURIs = MutableLiveData<List<Uri>>(emptyList())
    val imageURIs: LiveData<List<Uri>> get() = _imageURIs

    private val _tempUri = MutableLiveData<Uri?>()
    val tempUri: LiveData<Uri?> get() = _tempUri

    fun addImageUri(uri: Uri) {
        _imageURIs.value = _imageURIs.value?.plus(uri)
    }

    fun createTempUri() {
        _tempUri.value = cameraRepository.createImageFile()
    }
}
