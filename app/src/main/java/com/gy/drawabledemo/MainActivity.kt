package com.gy.drawabledemo

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gy.drawabledemo.page.CustomDrawableActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener(this)
        btn_material_components_shape.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when(v.id){
            R.id.btn1 -> {
                val intent = Intent(this,CustomDrawableActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_material_components_shape -> {
                val intent = Intent(this,MaterialComponentShapeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}