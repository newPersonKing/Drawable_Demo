package com.gy.drawabledemo

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.shape.*
import com.gy.drawabledemo.treatment.*
import kotlinx.android.synthetic.main.activity_material_component_shape.*

// android:clipChildren 不生效 那就试试把爷爷节点也设置一下 即使爷爷节点很大
class MaterialComponentShapeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_component_shape)

        roundedCornerTreatment()

        innerCutCornerTreatment()

        innerRoundCornerTreatment()

        extraRoundCornerTreatment()

        argEdgeTreatment()

        quadEdgeTreatment()

        cutCornerTreatment()

        triangleEdgeTreatment()

        offsetEdgeTreatment()

        markerEdgeTreatment()

        setShadow()
    }

    private fun roundedCornerTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllCorners(RoundedCornerTreatment())
            .setAllCornerSizes(20f)
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.YELLOW)
            paintStyle = Paint.Style.FILL
        }

        roundedCornerTreatment.background = backgroundDrawable
    }

    private fun innerCutCornerTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllCorners(InnerCutCornerTreatment())
            .setAllCornerSizes(20f)
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.RED)
            paintStyle = Paint.Style.FILL
        }

        innerCutCornerTreatment.background = backgroundDrawable
    }

    private fun innerRoundCornerTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllCorners(InnerRoundCornerTreatment())
            .setAllCornerSizes(20f)
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.GREEN)
            paintStyle = Paint.Style.FILL
        }

        innerRoundCornerTreatment.background = backgroundDrawable
    }

    private fun extraRoundCornerTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllCorners(ExtraRoundCornerTreatment())
            .setAllCornerSizes(20f)
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.GREEN)
            paintStyle = Paint.Style.FILL
        }

        extraRoundCornerTreatment.background = backgroundDrawable
    }

    private fun argEdgeTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
//            .setAllCorners(ExtraRoundCornerTreatment())
            .setAllEdges(ArgEdgeTreatment(20f,false))
            .setLeftEdge(ArgEdgeTreatment(30f,true))
//            .setAllCornerSizes(20f)
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.GREEN)
            paintStyle = Paint.Style.FILL
        }

        argEdgeTreatment.background = backgroundDrawable
    }

    private fun quadEdgeTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
//            .setAllCorners(ExtraRoundCornerTreatment())
            .setAllEdges(QuadEdgeTreatment(20f))
//            .setAllCornerSizes(20f)
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.GREEN)
            paintStyle = Paint.Style.FILL
        }

        quadqEdgeTreatment.background = backgroundDrawable
    }

    private fun cutCornerTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllCorners(CutCornerTreatment(20f))
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.GREEN)
            paintStyle = Paint.Style.FILL
        }

        cutCornerTreatment.background = backgroundDrawable
    }

    private fun triangleEdgeTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllEdges(TriangleEdgeTreatment(20f,false))
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.BLUE)
            paintStyle = Paint.Style.FILL
        }

        triangleEdgeTreatment.background = backgroundDrawable
    }

    private fun offsetEdgeTreatment(){
        // OffsetEdgeTreatment  沿着边偏移
        val shapePathModel = ShapeAppearanceModel.builder()
            .setLeftEdge(OffsetEdgeTreatment(TriangleEdgeTreatment(20f,false),100f))
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.MAGENTA)
            paintStyle = Paint.Style.FILL
        }

        offsetEdgeTreatment.background = backgroundDrawable
    }

    private fun markerEdgeTreatment(){
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllEdges(MarkerEdgeTreatment(30f))
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.GRAY)
            paintStyle = Paint.Style.FILL
        }

        markerEdgeTreatment.background = backgroundDrawable
    }

    private fun setShadow(){
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllCorners(RoundedCornerTreatment())
            .setAllCornerSizes(32f)
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.parseColor("#05bebebe"))
            paintStyle = Paint.Style.FILL
            shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
            initializeElevationOverlay(this@MaterialComponentShapeActivity)
            elevation = 32f
            setShadowColor(Color.parseColor("#D2D2D2"))
        }

        container.background = backgroundDrawable
    }
}