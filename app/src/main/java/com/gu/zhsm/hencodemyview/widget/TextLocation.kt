package com.gu.zhsm.hencodemyview.widget

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.gu.zhsm.hencodemyview.Utils

/**
 * Created by GuZhC on 2018/10/25.
 * describe:
 */
class TextLocation(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val RING_WIDTH = Utils.dp2px(8f)
    private val RADIUS = Utils.dp2px(60f)
    private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
    private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")

    var postionX: Float = 160f
    var postionY = Utils.dp2px(80f)
    var rect = Rect()
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val fontMetrice = Paint.FontMetrics()

    init {


        paint.textSize = Utils.dp2px(30f)
        paint.typeface = Typeface.createFromAsset(getContext().assets, "Quicksand-Regular.ttf")
        paint.getFontMetrics(fontMetrice)
        paint.textAlign = Paint.Align.CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        postionX = width / 2.toFloat()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_COLOR
        paint.strokeWidth = RING_WIDTH
        canvas!!.drawCircle(postionX, postionY, RADIUS, paint)

        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(postionX - RADIUS, postionY - RADIUS, postionX + RADIUS,
                postionY + RADIUS, -90f, 225f, false, paint)

        paint.textSize = Utils.dp2px(14f)
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
        paint.getTextBounds("useTextBounds", 0, "useTextBounds".length, rect)
        val offest = (rect.top+rect.bottom)/2
        canvas.drawText("useTextBounds",postionX,postionY-offest,paint)
    }
}