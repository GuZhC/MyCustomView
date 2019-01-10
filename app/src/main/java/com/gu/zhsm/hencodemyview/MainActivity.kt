package com.gu.zhsm.hencodemyview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
////       is AnimationView
//        view.setOnClickListener {
//            var bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 45f)
//            bottomFlipAnimator.duration = 1500
//
//            var flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270f)
//            flipRotationAnimator.duration = 1500
//
//            var topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", - 45f)
//            topFlipAnimator.duration = 1500
//
//            var animatorSet =  AnimatorSet()
//            animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator)
//            animatorSet.startDelay = 1000
//            animatorSet.start()
//        }


    }
}
