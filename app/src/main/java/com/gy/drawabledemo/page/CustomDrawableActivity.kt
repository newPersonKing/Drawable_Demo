package com.gy.drawabledemo.page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gy.drawabledemo.R
import com.gy.drawabledemo.drawable.AnimLetterDrawable
import kotlinx.android.synthetic.main.activity_cus_drawable.*


class CustomDrawableActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cus_drawable)

        val drawable = AnimLetterDrawable()
        btn1.background = drawable
        drawable.start()

        val textDrawable = AnimLetterDrawable()
        btn_text.background = textDrawable
        textDrawable.start()
    }

}