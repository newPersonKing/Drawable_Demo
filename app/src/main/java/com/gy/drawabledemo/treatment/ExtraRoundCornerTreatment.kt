package com.gy.drawabledemo.treatment

import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.ShapePath

class ExtraRoundCornerTreatment : CornerTreatment() {
    override fun getCornerPath(shapePath: ShapePath, angle: Float, f: Float, size: Float) {
        val radius = size * f
        shapePath.reset(0f, radius, 180f, 180 - angle)
        shapePath.addArc(-radius, -radius, radius, radius, angle, 270f)
    }
}
