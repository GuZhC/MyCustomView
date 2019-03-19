package com.gu.zhsm.mycustomview

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue

/**
 * Created by GuZhC on 2018/10/23.
 * describe:
 */
 object Utils{
    /**
     * dip转pix
     *
     * @param dpValue
     * @return
     */
    fun dp2px( dpValue: Float): Float {
       return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,Resources.getSystem().displayMetrics)
    }

    fun getAvatar(res: Resources, width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, R.drawable.my_head, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(res, R.drawable.my_head, options)
    }

    fun getZForCamera(): Float {
        return -6 * Resources.getSystem().displayMetrics.density
    }
    /**
     * pix转dip
     */
    fun pix2dip(context: Context, pix: Int): Int {
        val densityDpi = getResources(context).getDisplayMetrics().density
        return (pix / densityDpi + 0.5f).toInt()
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        return getResources(context).getDisplayMetrics().widthPixels
    }

    /**
     * 获得屏幕的高度
     *
     * @return
     */
    fun getScreenHeidth(context: Context): Int {
        return getResources(context).getDisplayMetrics().heightPixels
    }

    /**
     * 获得资源
     */
    fun getResources(context: Context): Resources {
        return context.resources
    }

}