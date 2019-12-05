package com.gojek.waetherdemo.utils

import android.content.Context
import android.view.View

import android.view.animation.AnimationUtils
import android.view.animation.Animation
import com.gojek.waetherdemo.R


object Utils {


    fun animateUp(context: Context, view: View) {
        val bottomUp = AnimationUtils.loadAnimation(
            context,
            R.anim.bottom_up
        )
        view.startAnimation(bottomUp)
        view.visibility = View.VISIBLE

    }

    fun animateRotation(context: Context, view: View) {
        val bottomUp = AnimationUtils.loadAnimation(
            context,
            R.anim.rotate
        )
        view.startAnimation(bottomUp)
        view.visibility = View.VISIBLE

    }
}