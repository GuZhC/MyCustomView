package com.gu.zhsm.mycustomview.widget

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.gu.zhsm.mycustomview.R
import com.gu.zhsm.mycustomview.Utils

/**
 * Created by GuZhC on 2018/10/23.
 * describe:
 */
class CiclerImage(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val mWidth = Utils.dp2px(300f)
    val mPadding = Utils.dp2px(36f)
    val sideW = Utils.dp2px(10f)

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var xfermoder = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    var bitmap: Bitmap
    var savedArea = RectF()

    init {
        bitmap = getAvatar(mWidth.toInt())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        savedArea.set(mPadding,mPadding,mPadding+mWidth,mPadding+mWidth)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawOval(mPadding,mPadding,mPadding+mWidth,mPadding+mWidth,paint)
        var saved = canvas?.saveLayer(savedArea,paint)
        canvas!!.drawOval(mPadding+sideW,mPadding+sideW,mPadding+mWidth-sideW,mPadding+mWidth-sideW,paint)
        paint.xfermode = xfermoder
        canvas!!.drawBitmap(bitmap,mPadding,mPadding,paint)
        paint.xfermode = null
        canvas.restoreToCount(saved!!)

    }

    private fun getAvatar(width: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.my_head, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = (width+Utils.dp2px(20f)).toInt()
        return BitmapFactory.decodeResource(resources, R.drawable.my_head, options)

    }
}