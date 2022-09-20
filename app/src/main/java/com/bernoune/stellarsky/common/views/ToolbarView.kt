package com.bernoune.stellarsky.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.widget.Toolbar
import com.bernoune.stellarsky.R
import kotlinx.android.synthetic.main.toolbar_layout.view.*



class ToolbarView : Toolbar {

    /**
    * VIEW ATTRIBUTES SECTION
    */
    private lateinit var titleTextView: TextView
    private lateinit var previousTextView: TextView

    /**
    * INTERACTION PROPERTY SECTION
    */

    var toolbarInteraction: ToolbarInteraction? = null

    constructor(@NonNull context: Context) : super(context)

    constructor(@NonNull context: Context, @NonNull attr: AttributeSet) : super(context, attr) {
        val view = LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this)
        titleTextView = view.toolbar_title
        previousTextView = view.toolbar_previous
        previousTextView.setOnClickListener {
            toolbarInteraction?.onBack()
        }
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun hidePrevious() {
        previousTextView.visibility = View.INVISIBLE
        previousTextView.isEnabled = false
        previousTextView.isClickable = false
    }

    fun showPrevious() {
        previousTextView.visibility = View.VISIBLE
        previousTextView.isEnabled = true
        previousTextView.isClickable = true
    }


    interface ToolbarInteraction {
        fun onBack()
    }

}