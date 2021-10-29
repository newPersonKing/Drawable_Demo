package com.gy.drawabledemo.drawable

import android.graphics.*
import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.util.Log
import android.util.SparseArray
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


// Animatable2 一个接口  定义了 开始 结束 以及 动画 开始 结束 回调
class AnimLetterDrawable : Drawable(), Animatable2, Runnable {

    private class Size(val type: Int) : ReadWriteProperty<AnimLetterDrawable, Float?> {
        private var prop: Float? = null
        override fun getValue(thisRef: AnimLetterDrawable, property: KProperty<*>): Float? {
            return prop ?: thisRef.run {
                val rect = Rect()
                this.paint.getTextBounds(this.letters, 0, this.letters.length, rect)
                val s = when (type) {
                    0 -> rect.width()
                    else -> rect.height()
                }.toFloat()
                prop = s
                prop
            }
        }

        override fun setValue(thisRef: AnimLetterDrawable, property: KProperty<*>, value: Float?) {
            prop = value
        }
    }

    val tag = "AnimLetterDrawable"

    var letters: String = "ABBBBBBB"
        set(value) {
            field = value
            width = null
            height = null
            invalidateSelf()
        }

    var color: Int = Color.CYAN
        set(value) {
            field = value
            paint.color = value
            invalidateSelf()
        }

    var textSize: Float = 60f
        set(value) {
            field = value
            width = null
            height = null
            paint.textSize = value
            invalidateSelf()
        }

    private var frameIndex = 0

    private val totalFrames = 30 * 3 //3 second, 30frames per second

    // 起始点
    private val originalLetterLocations = SparseArray<PointF>()
    // 结束点
    private val finalLetterLocations = SparseArray<PointF>()

    // 获取Paint 测量的letters 的尺寸
    private var width by Size(0)
    private var height by Size(1)

    private val paint = Paint().apply {
        textSize = 60f
        color = Color.CYAN
    }

    override fun getIntrinsicHeight(): Int {
        return height?.toInt() ?: -1
    }

    override fun getIntrinsicWidth(): Int {
        return width?.toInt() ?: -1
    }


    override fun draw(canvas: Canvas) {
        Log.d(tag, "on draw,$letters , $height,$frameIndex")

        val progress = if (totalFrames > 1) {
            frameIndex.toFloat() / (totalFrames - 1).toFloat()
        } else {
            1f
        }

        for (i in letters.indices) {
            val endPoint: PointF = finalLetterLocations.get(i)
            val startPoint: PointF = originalLetterLocations.get(i)
            val x: Float = startPoint.x + (endPoint.x - startPoint.x) * progress
            val y: Float = startPoint.y + (endPoint.y - startPoint.y) * progress

            // drawText 绘制规则 设置的 x y 坐标为 text 的baseLine的位置 不是文字的左上角
            canvas.drawText(letters[i].toString(), x, y -  paint.descent(), paint)
        }
    }

    override fun setAlpha(alpha: Int) {
        //ignore
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        //ignore
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }


    // 这里返回的尺寸 应该是跟view 的尺寸是一致的
    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        Log.d(tag, "onBoundsChange, $bounds")

        height = bounds.height().toFloat()
        width = bounds.width().toFloat()

        calcLetterStartEndLocations()

        invalidateSelf()
    }

    // 计算每一个字母的 起始位置 与 最终位置
    private fun calcLetterStartEndLocations() {
        originalLetterLocations.clear()
        finalLetterLocations.clear()

        val height = this.height ?: throw IllegalStateException("height cannot be null")
        val width = this.width ?: throw IllegalStateException("width cannot be null")

        val centerY: Float = height / 2f + paint.textSize / 2

        val totalLength = paint.measureText(letters)
        val startX = (width - totalLength) / 2

        var currentStartX = startX

        for (i in letters.indices) {
            val str: String = letters[i].toString()
            val currentLength: Float = paint.measureText(str)


            originalLetterLocations.put(
                i, PointF(
                    Math.random().toFloat() * width, Math.random()
                        .toFloat() * height
                )
            )

            finalLetterLocations.put(i, PointF(currentStartX, centerY))
            currentStartX += currentLength

        }
    }


    private val animationCallbacks: MutableSet<Animatable2.AnimationCallback> = linkedSetOf()

    private var mAnimating: Boolean = false

    private fun setFrame(frame: Int, unschedule: Boolean, animate: Boolean) {
        if (frame >= totalFrames) {
            return
        }
        mAnimating = animate
        frameIndex = frame
//        Log.i("log_AnimLetterDrawable","setFrame:start===$mAnimating，frameIndex：$frameIndex")
        if (unschedule || animate) {
            unscheduleSelf(this)
        }
        if (animate) {
            // Unscheduling may have clobbered these values; restore them
            frameIndex = frame


            // SystemClock.uptimeMillis() 代表手机开机到当前的时间总数 单位是毫秒
            // View 实现了 Drawable.Callback 在指定时间之后 会 回调what
            scheduleSelf(this, SystemClock.uptimeMillis() + durationPerFrame)
        }
        invalidateSelf()
    }

    private fun nextFrame(unschedule: Boolean) {
        var nextFrame: Int = frameIndex + 1
        val isLastFrame = nextFrame + 1 == totalFrames
        if (nextFrame + 1 > totalFrames) {
            nextFrame = totalFrames - 1
        }

        setFrame(nextFrame, unschedule, !isLastFrame)
    }

    override fun getCallback(): Callback? {
        val  callback =  super.getCallback()
        return callback
    }

    private val durationPerFrame = 3000 / totalFrames

    // 开始动画
    override fun start() {
        Log.d(tag, "start called")
        mAnimating = true

        Log.i("log_AnimLetterDrawable","start===$isRunning")
        if (!isRunning) {
            // Start from 0th frame.
            setFrame(
                frame = 0, unschedule = false, animate = false
            )
        } else {
            setFrame(
                frame = 0, unschedule = false, animate = true
            )
        }
    }

    override fun stop() {
        mAnimating = false

        if (isRunning) {
            frameIndex = 0
            //un-schedule it at first
            unscheduleSelf(this)

            setFrame(0, unschedule = true, animate = false)
        }
    }

    override fun isRunning(): Boolean {
        return mAnimating
    }

    override fun registerAnimationCallback(callback: Animatable2.AnimationCallback) {
        animationCallbacks.add(callback)
    }

    override fun unregisterAnimationCallback(callback: Animatable2.AnimationCallback): Boolean {
        return animationCallbacks.remove(callback)
    }

    override fun clearAnimationCallbacks() {
        animationCallbacks.clear()
    }

    override fun run() {
        Log.d(tag, "callback by schedule")
        Log.i("log_AnimLetterDrawable","run===")
        if (isRunning) {
            nextFrame(false)
        } else {
            //safe call
            setFrame(0, unschedule = true, animate = false)
        }

    }

}