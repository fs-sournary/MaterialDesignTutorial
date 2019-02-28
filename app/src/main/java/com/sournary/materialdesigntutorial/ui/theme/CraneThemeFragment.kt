package com.sournary.materialdesigntutorial.ui.theme

import com.sournary.materialdesigntutorial.R

/**
 * Created in 9/23/19 by Sang
 * Description:
 */
class CraneThemeFragment : BaseThemeFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_crane_shape_theme

    override fun getTheme(): Int = R.style.Theme_MaterialTutorial_ShapeTheme_Crane

    override fun getCurrentDestId(): Int = R.id.crane_theme_dest
}
