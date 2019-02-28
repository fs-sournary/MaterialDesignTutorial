package com.sournary.materialdesigntutorial.ui.theme

import com.sournary.materialdesigntutorial.R

/**
 * Created in 9/23/19 by Sang
 * Description:
 */
class FortNightlyThemeFragment : BaseThemeFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_fort_nightly_shape_theme

    override fun getTheme(): Int = R.style.Theme_MaterialTutorial_Theme_FortNightly

    override fun getCurrentDestId(): Int = R.id.fort_nightly_theme_dest
}
