package com.gu.zhsm.mycustomview.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.gu.zhsm.mycustomview.Utils

/**
 * Created by GuZhC on 2018/10/29.
 * describe:
 */
class AnimationView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val PADDING = Utils.dp2px(100f)
    private val IMAGE_WIDTH = Utils.dp2px(200f)

    internal var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    internal var camera = Camera()

    internal var topFlip = 0f
    internal var bottomFlip = 0f
    internal var flipRotation = 0f
    var  animatorSet=  AnimatorSet()

    init{
        camera.setLocation(0f, 0f, Utils.getZForCamera()) // -8 = -8 * 72
        var bottomFlipAnimator = ObjectAnimator.ofFloat(this, "bottomFlip", 45f)
        bottomFlipAnimator.duration = 1500

        var flipRotationAnimator = ObjectAnimator.ofFloat(this, "flipRotation", 270f)
        flipRotationAnimator.duration = 1500

        var topFlipAnimator = ObjectAnimator.ofFloat(this, "topFlip", - 45f)
        topFlipAnimator.duration = 1500

        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator)
        animatorSet.startDelay = 500
    }

    fun getTopFlip(): Float {
        return topFlip
    }

    fun setTopFlip(topFlip: Float) {
        this.topFlip = topFlip
        invalidate()
    }

    fun getBottomFlip(): Float {
        return bottomFlip
    }

    fun setBottomFlip(bottomFlip: Float) {
        this.bottomFlip = bottomFlip
        invalidate()
    }

    fun getFlipRotation(): Float {
        return flipRotation
    }

    fun setFlipRotation(flipRotation: Float) {
        this.flipRotation = flipRotation
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制上半部分
        canvas.save()
        canvas.translate(PADDING + IMAGE_WIDTH / 2, PADDING + IMAGE_WIDTH / 2)
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateX(topFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0f)
        canvas.rotate(flipRotation)
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2))
        canvas.drawBitmap(Utils.getAvatar(resources, IMAGE_WIDTH.toInt()), PADDING, PADDING, paint)
        canvas.restore()

        // 绘制下半部分
        canvas.save()
        canvas.translate(PADDING + IMAGE_WIDTH / 2, PADDING + IMAGE_WIDTH / 2)
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateX(bottomFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-IMAGE_WIDTH, 0f, IMAGE_WIDTH, IMAGE_WIDTH)
        canvas.rotate(flipRotation)
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2))
        canvas.drawBitmap(Utils.getAvatar(resources, IMAGE_WIDTH.toInt()), PADDING, PADDING, paint)
        canvas.restore()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animatorSet.start()
    }
}