package com.shpzdsh.musicapp.core

interface ActivityStateProcessor {

    fun processLoading(state: Boolean)

    fun processError(message: String, onReloadPage: () -> Unit)
}