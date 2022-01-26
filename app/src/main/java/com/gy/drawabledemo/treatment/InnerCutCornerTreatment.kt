package com.gy.drawabledemo.treatment

import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.ShapePath

class InnerCutCornerTreatment : CornerTreatment() {
    override fun getCornerPath(shapePath: ShapePath, angle: Float, f: Float, size: Float) {
        val radius = size * f

        // 四个角返回的 angle 都是 90
        // 是不是可以理解为 四个角都是以自己为中心
        shapePath.reset(0f, radius, 180f, 180 - angle)
        shapePath.lineTo(radius, radius)
        shapePath.lineTo(radius, 0f)
    }
}