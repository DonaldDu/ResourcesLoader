package com.dhy.resourcesadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhy.resources.loader.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btShow2.setOnClickListener {
            displayAssets()
        }
    }
}