package com.gu.zhsm.hencodemyview.widget

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.gu.zhsm.hencodemyview.Utils

/**
 * Created by GuZhC on 2018/10/26.
 * describe:
 */
class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var camera = Camera()
    init {
        camera.rotateX(45f)
        camera.setLocation(0f,0f,Utils.getZForCamera())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 绘制上半部分
        canvas!!.save()
        canvas.translate((100 + 600 / 2).toFloat(), (100 + 600 / 2).toFloat())
        canvas.rotate(-20f)
        canvas.clipRect(-600, -600, 600, 0)
        canvas.rotate(20f)
        canvas.translate((-(100 + 600 / 2)).toFloat(), (-(100 + 600 / 2)).toFloat())
        canvas.drawBitmap(Utils.getAvatar(resources, 600), 100f, 100f, paint)
        canvas.restore()

        // 绘制下半部分
        canvas.save()
        canvas.translate((100 + 600 / 2).toFloat(), (100 + 600 / 2).toFloat())
        canvas.rotate(-20f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-600, 0, 600, 600)
        canvas.rotate(20f)
        canvas.translate((-(100 + 600 / 2)).toFloat(), (-(100 + 600 / 2)).toFloat())
        canvas.drawBitmap(Utils.getAvatar(resources, 600), 100f, 100f, paint)
        canvas.restore()
    }
}