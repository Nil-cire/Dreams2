package com.nilcire.dreamscometrue.util

import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.TextView

class ViewUtil {

    companion object {
        fun isTextViewEllipse(textView: TextView): Boolean {
            textView.layout?.let {
                if (textView.layout.lineCount > 0) {
                    Log.d("TAG", "isTextViewEllipse: ")
                    return (textView.layout.getEllipsisCount(textView.lineCount - 1) > 0)
                }
            }
            return false
        }
    }
}