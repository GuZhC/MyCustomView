package com.gu.zhsm.hencodemyview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.gu.zhsm.hencodemyview.Utils

/**
 * Created by GuZhC on 2018/10/23.
 * describe:
 */
class YuanBing(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val radius = Utils.dp2px(150f)
    val length = Utils.dp2px(20f)
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bounds = RectF()
    var angles = arrayOf(60, 100, 120, 80)
    var colors = arrayOf(Color.parseColor("#2979FF"), Color.parseColor("#C2185B")
            , Color.parseColor("#009688"), Color.parseColor("#FF8F00"))

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bounds.set(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var currentAngle = 0
        for (i in angles.indices){
            paint.color=colors[i]
            canvas!!.save()
            if (i == 0)
                canvas.translate((Math.cos(Math.toRadians((currentAngle+angles[i]/2).toDouble()))*length).toFloat(),
                        (Math.sin(Math.toRadians((currentAngle+angles[i]/2).toDouble()))*length).toFloat())
            canvas.drawArc(bounds, currentAngle.toFloat(), angles[i].toFloat(),true,paint)
            canvas.restore()
            currentAngle +=angles[i]
        }
    }

}