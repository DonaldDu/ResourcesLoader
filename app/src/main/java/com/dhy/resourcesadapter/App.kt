package com.dhy.resourcesadapter

import android.app.Application
import android.content.Context
import android.content.res.ResourcesLoader
import java.io.File

class App : Application() {
    private val file by lazy { File(filesDir, "assets.apk") }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        prepareFile()
        ResourcesLoader.instance.initFastCompare(assets, listOf(file.absolutePath))
    }

    private fun prepareFile() {
        if (file.exists()) return
        file.createNewFile()
        val input = assets.open("assets.apk")
        input.use {
            file.writeBytes(it.readBytes())
        }
    }
}