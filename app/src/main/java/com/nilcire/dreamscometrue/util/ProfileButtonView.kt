package com.nilcire.dreamscometrue.util

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.nilcire.dreamscometrue.R

class ProfileButtonView(context: Context, attr: AttributeSet?): CardView(context, attr) {

    init {
        val linearLayout = LinearLayout(context)
        val imageHolder = ConstraintLayout(context)
        val imageHolderConstraintSet = ConstraintSet()
        val imageview = ImageView(context)
        val textview = TextView(context)
        attr?.let {
            val resource = context.obtainStyledAttributes(attr, R.styleable.ProfileButtonView)
            resource.getDrawable(R.styleable.ProfileButtonView_iconImage)?.let { imageview.background = it }
            resource.getString(R.styleable.ProfileButtonView_title)?.let { textview.text = it }
//            resource.getString(R.styleable.ProfileButtonView_holderBackgroundColor)?.let { this.cardBackgroundColor = it }
        }

        imageHolder.addView(imageview)
        imageview.scaleType = ImageView.ScaleType.CENTER_CROP
        imageHolderConstraintSet.apply {
            imageHolder.id = 999
            imageview.id = 998
            this.clone(imageHolder)
            this.setDimensionRatio(998, "1:1")
            this.connect(998, ConstraintSet.START, 999, ConstraintSet.START)
            this.connect(998, ConstraintSet.END, 999, ConstraintSet.END)
            this.constrainWidth(998, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT)
            this.constrainHeight(998, ConstraintLayout.LayoutParams.MATCH_PARENT)
            this.applyTo(imageHolder)
        }

        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.addView(imageHolder)
        linearLayout.addView(textview)
        val imageHolderParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2f)
        val textViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f)

        imageHolder.layoutParams = imageHolderParams

        textview.layoutParams = textViewParams
        textview.gravity = Gravity.CENTER

        this.addView(linearLayout)
        val linearLayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        linearLayout.layoutParams = linearLayoutParams

    }

}