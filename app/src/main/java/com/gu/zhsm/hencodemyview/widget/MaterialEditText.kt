package com.gu.zhsm.hencodemyview.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import com.gu.zhsm.hencodemyview.R
import com.gu.zhsm.hencodemyview.Utils

/**
 * Created by GuZhC on 2018/11/2.
 * describe:
 */
class MaterialEditText(context: Context?, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private val TEXT_SIZE = Utils.dp2px(12f)
    private val TEXT_MARGIN = Utils.dp2px(8f)
    private val TEXT_VERTICAL_OFFSET = Utils.dp2px(22f).toInt()
    private val TEXT_HORIZONTAL_OFFSET = Utils.dp2px(5f).toInt()
    private val TEXT_ANIMATION_OFFSET = Utils.dp2px(16f).toInt()
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var floatingLabelShown: Boolean = false
    var floatingLabelFraction: Float = 0.toFloat()
        set(value) {
            field = value
            invalidate()
        }
    private var animator: ObjectAnimator? = null
    private var useFloatingLabel: Boolean = false
    var backgroundPadding = Rect()

    init {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        typedArray.recycle()

        paint.textSize = TEXT_SIZE
        onUseFloatingLabelChanged()
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (useFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        floatingLabelShown = false
                        getAnimator()!!.reverse()
                    } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                        floatingLabelShown = true
                        getAnimator()!!.start()
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

    }

    private fun onUseFloatingLabelChanged() {
        background.getPadding(backgroundPadding)
        if (useFloatingLabel) {
            setPadding(paddingLeft, (backgroundPadding.top.toFloat() + TEXT_SIZE + TEXT_MARGIN).toInt(), paddingRight, paddingBottom)
        } else {
            setPadding(paddingLeft, backgroundPadding.top, paddingRight, paddingBottom)
        }
    }



    fun setUseFloatingLabel(useFloatingLabel: Boolean) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel
            onUseFloatingLabelChanged()
        }
    }

    private fun getAnimator(): ObjectAnimator? {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(this@MaterialEditText, "floatingLabelFraction", 0f, 1f)
        }
        return animator!!
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.alpha = (0xff * floatingLabelFraction).toInt()
        val extraOffset = TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction)
        canvas.drawText(hint.toString(), TEXT_HORIZONTAL_OFFSET.toFloat(), TEXT_VERTICAL_OFFSET + extraOffset, paint)
    }
}