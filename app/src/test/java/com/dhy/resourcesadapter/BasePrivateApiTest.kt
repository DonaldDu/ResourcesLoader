package com.dhy.resourcesadapter

import android.content.res.AssetManager
import org.junit.Test
import org.robolectric.annotation.Config

@Config(minSdk = 21)
open class BasePrivateApiTest {
    @Test
    fun addAssetPath() {
        AssetManager::class.java.getMethod("addAssetPath", String::class.java)
    }

    @Test
    fun getSystem() {
        AssetManager::class.java.getMethod("getSystem")
    }
}