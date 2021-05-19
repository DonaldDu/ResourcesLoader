package com.dhy.resourcesadapter

import android.app.Application
import android.content.res.AssetManager
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(minSdk = 28, manifest = Config.NONE, application = Application::class)
@RunWith(RobolectricTestRunner::class)
class PrivateApi28Test : BasePrivateApiTest() {
    @Test
    fun getApkAssets() {
        AssetManager::class.java.getMethod("getApkAssets")
    }

    @Test
    fun getAssetPath() {
        val ApkAssets = Class.forName("android.content.res.ApkAssets")
        ApkAssets.getMethod("getAssetPath")
    }

    @Test
    fun assetManagerBuilder() {
        val AssetManagerBuilder = Class.forName("android.content.res.AssetManager\$Builder")
        println(AssetManagerBuilder.name)
    }
}