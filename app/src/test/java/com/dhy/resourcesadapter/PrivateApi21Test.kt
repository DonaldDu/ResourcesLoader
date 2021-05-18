package com.dhy.resourcesadapter

import android.app.Application
import android.content.res.AssetManager
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(minSdk = 21, maxSdk = 27, manifest = Config.NONE, application = Application::class)
@RunWith(RobolectricTestRunner::class)
class PrivateApi21Test : BasePrivateApiTest() {
    @Test
    fun mStringBlocks() {
        AssetManager::class.java.getDeclaredField("mStringBlocks")
    }

    @Test
    fun getCookieName() {
        AssetManager::class.java.getMethod("getCookieName", Int::class.javaPrimitiveType)
    }
}