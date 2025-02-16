package com.shpzdsh.musicapp.core

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shpzdsh.musicapp.data.api.models.TrackDetailsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseMusicViewModel : ViewModel() {

    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage : MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage = _errorMessage.asStateFlow()


    protected fun processError(throwable: Throwable) {
        Log.e(BaseMusicViewModel::class.java.name, throwable.toString())
        _errorMessage.value = throwable.message
    }

    protected fun startLoad() {
        _isLoading.value = true
    }

    protected fun <T> Result<T>.onEndLoad(): Result<T> {
        _isLoading.value = false
        return this
    }
}