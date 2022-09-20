package com.bernoune.stellarsky.common.extensions

import android.widget.ImageView




fun ImageView.defineDrawableFrom(iconName: String?) {
    val resId =
        resources.getIdentifier(
            "ic_$iconName",
            "drawable",
            "com.bernoune.weathertestpsa"
        )
    this.setImageResource(resId)
}