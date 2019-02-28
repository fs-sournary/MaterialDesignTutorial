package com.sournary.materialdesigntutorial.ui.theme

import com.sournary.materialdesigntutorial.R

/**
 * Created in 9/23/19 by Sang
 * Description:
 */
class ShrineThemeFragment : BaseThemeFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_shrine_shape_theme

    override fun getTheme(): Int = R.style.Theme_MaterialTutorial_Shrine

    override fun getCurrentDestId(): Int = R.id.shrine_theme_dest
}
