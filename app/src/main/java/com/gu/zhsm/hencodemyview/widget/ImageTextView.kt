package com.gu.zhsm.hencodemyview.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.gu.zhsm.hencodemyview.R
import com.gu.zhsm.hencodemyview.Utils

/**
 * Created by GuZhC on 2018/10/26.
 * describe:
 */
class ImageTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val IMAGE_WIDTH = Utils.dp2px(140f)
    private val IMAGE_OFFSET = Utils.dp2px(30f)

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap:Bitmap
    var fontMetrics = Paint.FontMetrics()
    val text = "      对于我这样的凡人而言，很难想象张小龙的内心深处有怎样的波澜。一个人一辈子做成一个Foxmail已经足够难能可贵，可以抱着孙子放在膝头吹嘘一整个晚年。但是，他不单做成了Foxmail，更做成了QQmail，拥有数以亿计的用户；做成拥有过亿用户的单一互联网产品，一个人足以笑傲江湖，成为传奇人物，但是他又做了微信，开启了中国人的移动互联网时代。作为产品经理，他已经是世间最顶级的那几个人之一。当你认为他的人生已经登上一名理工科学生的巅峰时，他却在体育界拿来了一个冠军，告诉你除了头脑之外，他也能完美控制自己的身体，就像他在小游戏跳一跳里随手跳到一万分以上一样。"
    var cutWidth = FloatArray(1)
    init {
        bitmap = getAvatar(IMAGE_WIDTH.toInt())
        paint.textSize = Utils.dp2px(12f)
        paint.getFontMetrics(fontMetrics)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawBitmap(bitmap, width - IMAGE_WIDTH, IMAGE_OFFSET, paint)
        val length = text.length
        var verticalOffset = -fontMetrics.top
        var start = 0
        while (start < length) {
            val maxWidth: Int
            val textTop = verticalOffset + fontMetrics.top
            val textBottom = verticalOffset + fontMetrics.bottom
            if (textTop > IMAGE_OFFSET && textTop < IMAGE_OFFSET + bitmap.height || textBottom > IMAGE_OFFSET && textBottom < IMAGE_OFFSET + bitmap.height) {
                // 文字和图片在同一行
                maxWidth = (width - bitmap.width).toInt()
            } else {
                // 文字和图片不在同一行
                maxWidth = width
            }
            val count = paint.breakText(text, start, length, true, maxWidth.toFloat(), cutWidth)
            canvas.drawText(text, start, start + count, 0f, verticalOffset, paint)
            start += count
            verticalOffset += paint.fontSpacing
        }
    }

     fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.zxl_image, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.zxl_image, options)
    }
}