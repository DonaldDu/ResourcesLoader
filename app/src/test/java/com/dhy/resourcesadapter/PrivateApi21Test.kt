package com.dhy.resourcesadapter

import android.content.res.AssetManager
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(minSdk = 21, maxSdk = 27, manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class PrivateApi21Test {
    @Test
    fun mStringBlocks() {
        AssetManager::class.java.getDeclaredField("mStringBlocks")
    }

    @Test
    fun getCookieName() {
        AssetManager::class.java.getMethod("getCookieName", Int::class.javaPrimitiveType)
    }

    @Test
    fun addAssetPath() {
        AssetManager::class.java.getMethod("addAssetPath", String::class.java)
    }
}