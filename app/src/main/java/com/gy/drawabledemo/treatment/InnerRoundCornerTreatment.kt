package com.gy.drawabledemo.treatment

import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.ShapePath

class InnerRoundCornerTreatment : CornerTreatment() {
    override fun getCornerPath(shapePath: ShapePath, angle: Float, f: Float, size: Float) {
        val radius = size * f
        shapePath.reset(0f, radius, 180f, 180 - angle)
        // 已 -radius, -radius, radius, radius 的中心为圆心 起始角度是 angle 一般是90度  逆坐标系旋转90
        shapePath.addArc(-radius, -radius, radius, radius, angle, -90f)
    }
}