package com.gy.drawabledemo.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.gy.drawabledemo.R


// defStyleAttr 不能为空 所以需要设置默认值 不然不能直接在xml 里面使用
class GradientTextView @JvmOverloads constructor
    (context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) :
    AppCompatTextView(context, attrs, defStyleAttr) {

    private val gradientDrawable = GradientDrawable()
    private val INVALID_COLOR = 0
    init {

        attrs?.apply {

            val typedArray  = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView)

            // 边框 宽度
            val strokeWidth = typedArray.getDimensionPixelSize(
                R.styleable.GradientTextView_border_width,
                0
            )

            // 边框颜色
            val strokeColor = typedArray.getColor(
                R.styleable.GradientTextView_border_color,
                Color.TRANSPARENT
            )

            gradientDrawable.setStroke(strokeWidth, strokeColor)

            val borderRadius = typedArray.getDimensionPixelSize(
                R.styleable.GradientTextView_border_radius,
                0
            )
            gradientDrawable.cornerRadius  = borderRadius * 1.0f

            val background = typedArray.getColor(
                R.styleable.GradientTextView_solid_background,
                INVALID_COLOR
            )
            gradientDrawable.setColor(background)

            // background 有值 background优先
            if(background == INVALID_COLOR){
                val colorArray = mutableListOf<Int>()

                val startColor = typedArray.getColor(
                    R.styleable.GradientTextView_gradient_start_color,
                    INVALID_COLOR
                )
                if(startColor != INVALID_COLOR) colorArray.add(startColor)

                val centerColor = typedArray.getColor(
                    R.styleable.GradientTextView_gradient_center_color,
                    INVALID_COLOR
                )
                if(centerColor != INVALID_COLOR) colorArray.add(centerColor)

                val endColor = typedArray.getColor(
                    R.styleable.GradientTextView_gradient_end_color,
                    INVALID_COLOR
                )
                if(endColor != INVALID_COLOR) colorArray.add(endColor)

                val orientation = typedArray.getInt(R.styleable.GradientTextView_orientation,0)


                if(colorArray.isNotEmpty()&& colorArray.size > 1){
                    val array = IntArray(colorArray.size)
                    colorArray.forEachIndexed { index, i ->
                        array[index] = i
                    }
                    gradientDrawable.setColors(array)
                    gradientDrawable.orientation = resolveorientation(orientation)
                }else if(colorArray.isNotEmpty()){
                    gradientDrawable.setColor(colorArray[0])
                }
            }

            typedArray.recycle()
        }


        background = gradientDrawable

    }

    fun resolveorientation(orientation:Int): GradientDrawable.Orientation {

        return when(orientation){
            1 ->{
                return GradientDrawable.Orientation.TOP_BOTTOM
            }
            2 -> {
                return GradientDrawable.Orientation.TR_BL
            }
            3 -> {
                return GradientDrawable.Orientation.RIGHT_LEFT
            }
            4 -> {
                return GradientDrawable.Orientation.BR_TL
            }
            5 -> {
                return GradientDrawable.Orientation.BOTTOM_TOP
            }
            6 -> {
                return GradientDrawable.Orientation.BL_TR
            }
            7 -> {
                return GradientDrawable.Orientation.LEFT_RIGHT
            }
            8 -> {
                return GradientDrawable.Orientation.TR_BL
            }
            else ->{
                return GradientDrawable.Orientation.LEFT_RIGHT
            }
        }

    }

}