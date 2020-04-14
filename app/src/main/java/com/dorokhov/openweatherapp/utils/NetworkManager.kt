package com.dorokhov.openweatherapp.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager
@Inject
constructor(
    val application: Application
) {

    private val TAG = "AppDebug"

    // я знаю проблемку, в гугл предлагает теперь организовать проверку через колбэки, что немного мешает
    // на самом деле. Будет время -- можно решить проблему.
    fun isConnectedToTheInternet(): Boolean{
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try{
            return cm.activeNetworkInfo.isConnected
        }catch (e: Exception){
            Log.e(TAG, "isConnectedToTheInternet: ${e.message}")
        }
        return false
    }

}

