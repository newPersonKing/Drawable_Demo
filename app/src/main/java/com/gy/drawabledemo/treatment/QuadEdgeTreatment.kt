package com.gy.drawabledemo.treatment

import android.util.Log
import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath

// 贝塞尔曲线 开始点 结束点 控制点 开始点 结束点 不动 随着控制点位置不同生成不同的贝塞尔曲线
class QuadEdgeTreatment(val size: Float) : EdgeTreatment() {
    override fun getEdgePath(length: Float, center: Float, f: Float, shapePath: ShapePath) {
        shapePath.quadToPoint(center, size * f, length, 0f)
    }
}