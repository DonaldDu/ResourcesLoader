package com.dhy.resourcesadapter

import android.app.Application
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 21, manifest = Config.NONE, application = Application::class)
class AssetManagerBuilderTest {
    @Test
    fun test() {
        val clzz = javaClass.classLoader!!.loadClass("android.content.res.AssetManager\$Builder")
        println(clzz.name)
    }
}