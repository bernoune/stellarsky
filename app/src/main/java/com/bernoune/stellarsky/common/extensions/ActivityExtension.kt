package com.bernoune.stellarsky.common.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment



fun AppCompatActivity.replaceFragmentSafely(
    @IdRes layout: Int,
    fragment: Fragment,
    tag: String,
    addToBackStack: Boolean,
    allowStateLoss: Boolean = false
) {

    if (tag.isNotEmpty()) {
        val manager = supportFragmentManager
        if (manager.findFragmentByTag(tag) == null) {
            val transaction = manager.beginTransaction()
            transaction.replace(layout, fragment, tag)
            if (addToBackStack) transaction.addToBackStack(tag)
            when {
                !manager.isStateSaved -> transaction.commit()
                allowStateLoss -> transaction.commitAllowingStateLoss()
            }
            manager.executePendingTransactions()
        }
    }
}


