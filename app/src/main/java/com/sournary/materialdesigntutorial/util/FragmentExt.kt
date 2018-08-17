package com.sournary.materialdesigntutorial.util

import android.os.Build
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
fun Fragment.setSupportActionBar(toolBar: Toolbar, action: ActionBar.() -> Unit) {
    with(activity as AppCompatActivity) {
        setSupportActionBar(toolBar)
        supportActionBar?.action()
    }
}


fun Fragment.setActivityStatusBarColor(@ColorRes id: Int) {
    activity?.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, id)
        }
    }
}

fun Fragment.showSnackbar(
    message: String,
    isSetAction: Boolean = false,
    @StringRes actionMessage: Int = android.R.string.ok,
    listener: ((View) -> Unit) = {},
    duration: Int = Snackbar.LENGTH_SHORT
) {
    view?.let {
        val snackbar = Snackbar.make(it, message, duration)
        if (isSetAction) {
            snackbar.setAction(actionMessage, listener)
        }
        snackbar.show()
    }
}
