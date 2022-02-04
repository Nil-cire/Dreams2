package com.nilcire.dreamscometrue.util

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class EllipsisTextView(context: Context?, attrs: AttributeSet?) : TextView(context, attrs) {

    var layoutListener: OnLayoutListener? = null

    fun setOnLayoutListener(layoutListener: OnLayoutListener) {
        this.layoutListener = layoutListener
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        layoutListener?.onLayout()
    }

    interface OnLayoutListener {
        fun onLayout()
    }
}

