package com.dhy.resourcesadapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Resources
import android.content.res.ResourcesLoader
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dhy.resources.loader.R
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btShow.setOnClickListener {
            displayAssets()
        }
        btShow2.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    override fun getResources(): Resources {
        if (Build.VERSION.SDK_INT < 28) ResourcesLoader.instance.loadResources(assets)
        return super.getResources()
    }
}

fun Activity.displayAssets() {
    try {
        val inputStream = assets.open("assets.txt")
        val txt = inputStream.readBytes().toString(Charset.defaultCharset())
        inputStream.close()
        AlertDialog.Builder(this)
            .setTitle("Asset Content")
            .setMessage(txt)
            .show()
    } catch (e: Exception) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}