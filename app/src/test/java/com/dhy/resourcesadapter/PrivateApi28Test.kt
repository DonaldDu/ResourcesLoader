package com.dhy.resourcesadapter

import android.content.res.AssetManager
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(minSdk = 28, manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class PrivateApi28Test {
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
    fun addAssetPath() {
        AssetManager::class.java.getMethod("addAssetPath", String::class.java)
    }
}