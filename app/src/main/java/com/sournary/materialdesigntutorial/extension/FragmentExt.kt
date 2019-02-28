package com.sournary.materialdesigntutorial.extension

import android.os.Bundle
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.snackbar.Snackbar
import com.sournary.materialdesigntutorial.R

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
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

fun Fragment.navigateToDestAndClearCurrent(
    @IdRes fromDestId: Int,
    @IdRes toDestId: Int,
    args: Bundle? = null,
    @AnimRes enterAnim: Int = R.anim.anim_grow_fade_in_from_bottom,
    @AnimRes exitAnim: Int = R.anim.anim_fade_out,
    @AnimRes popEnterAnim: Int = R.anim.anim_fade_in,
    @AnimRes popExitAnim: Int = R.anim.anim_shrink_fade_out_from_bottom
) {
    val options = navOptions {
        anim {
            enter = enterAnim
            exit = exitAnim
            popEnter = popEnterAnim
            popExit = popExitAnim
        }
        popUpTo(fromDestId) { inclusive = true }
    }
    findNavController().navigate(toDestId, args, options)
}
