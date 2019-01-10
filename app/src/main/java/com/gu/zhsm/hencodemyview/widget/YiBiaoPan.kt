package com.gu.zhsm.hencodemyview.widget

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.gu.zhsm.hencodemyview.Utils

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
/**
 * Created by GuZhC on 2018/10/23.
 * describe:
 */
class YiBiaoPan(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
   var  paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val ANGLE = 120
    private val RADIUS = Utils.dp2px(150f)
    private val LENGTh = Utils.dp2px(100f)
    var dash = Path()
    var effect:PathDashPathEffect
    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = Utils.dp2px(2f)
        dash.addRect(0f,0f,Utils.dp2px(2f),Utils.dp2px(10f),Path.Direction.CW)
        var arc = Path()
        arc.addArc(width/2-RADIUS,height/2-RADIUS,width/2+RADIUS,
                height/2+RADIUS, (90+ANGLE/2).toFloat(), (360-ANGLE).toFloat())
       var pthMeasure = PathMeasure(arc,false)
        effect = PathDashPathEffect(dash,(pthMeasure.length-Utils.dp2px(2f))/20,
                0f,PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.drawArc(width/2-RADIUS,height/2-RADIUS,width/2+RADIUS,
                height/2+RADIUS, (90+ANGLE/2).toFloat(), (360-ANGLE).toFloat(),false,paint)

        paint.pathEffect = effect
        canvas!!.drawArc(width/2-RADIUS,height/2-RADIUS,width/2+RADIUS,
                height/2+RADIUS, (90+ANGLE/2).toFloat(), (360-ANGLE).toFloat(),false,paint)
        paint.pathEffect=null

        paint.color =Color.RED
        canvas.drawLine((width/2).toFloat(), (height/2).toFloat(),
                (Math.cos(Math.toRadians(getAngleFromMark(5).toDouble()))*LENGTh+width/2).toFloat(),
                (Math.sin(Math.toRadians(getAngleFromMark(5).toDouble()))*LENGTh+height/2).toFloat(),paint)
    }

    fun  getAngleFromMark(mark:Int):Int{
        return (90+(ANGLE/2).toFloat()+(360-ANGLE)/20*mark).toInt()
    }
}