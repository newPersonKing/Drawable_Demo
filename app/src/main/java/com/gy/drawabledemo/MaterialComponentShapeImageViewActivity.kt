package com.gy.drawabledemo

import android.graphics.Outline
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.android.synthetic.main.activity_shape_imageview.*

class MaterialComponentShapeImageViewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shape_imageview)

        codeCorner()

        viewOutlineProvider()

        viewOutLinePath()
    }

    private fun codeCorner(){
        val model = ShapeAppearanceModel.builder().setAllCornerSizes(ShapeAppearanceModel.PILL).build()
        iv_code_corner.shapeAppearanceModel = model
    }

    private fun viewOutlineProvider(){
        iv_view_outline_provider.outlineProvider = object : ViewOutlineProvider(){
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, 32f) // 圆角
//                outline.setOval(0, 0, view.width, view.height) // circle
            }
        }
        iv_view_outline_provider.clipToOutline = true
    }

    private fun viewOutLinePath(){
        viewOutLinePath.outlineProvider = object : ViewOutlineProvider(){
            override fun getOutline(view: View, outline: Outline) {
                val path = Path()
                view.elevation = 4f
                path.moveTo(-20f, -20f)
                path.lineTo(-20f, view.height.toFloat() + 20)
                path.lineTo(view.width.toFloat() + 20, view.height.toFloat() + 20)
                path.lineTo(view.width.toFloat() + 20, -20f)
                path.close()
                outline.setConvexPath(path)
            }
        }

        viewOutLinePath.clipToOutline = true

    }
}