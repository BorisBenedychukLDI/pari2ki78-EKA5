package com.parimka.machas.com

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

fun MainActivity2ki78.clickSetup2ki78 (
    uiComponents: (
    view2ki78: View
    ) -> Unit
    ) {
        findViewById<View>(R.id.v_2ki78).setOnClickListener {
            uiComponents(it)
        }
        findViewById<TextView>(R.id.tv_start_2ki78).setOnClickListener {
            uiComponents(it)
        }
}

fun MainActivity2ki78.animationBackGround2ki78 () {
    ValueAnimator.ofArgb(resources.getColor(R.color.white_2ki78), resources.getColor(R.color.theme_yellow_2_2ki78)).run {
        setEvaluator(ArgbEvaluator())
        duration = 5000
        addUpdateListener { findViewById<ConstraintLayout>(R.id.cl_second_2ki78).setBackgroundColor(it.animatedValue.toString().toInt()) }
        start()
    }
}