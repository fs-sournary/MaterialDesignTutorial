package com.sournary.materialdesigntutorial.ui.topappbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sournary.materialdesigntutorial.R

/**
 * Created on 2019-09-22 by Sang
 * Description:
 **/
class ScrollableTopAppbarTransparentStatusBarFragment : BaseTopAppbarFragment() {

    override fun getCurrentDestId(): Int = R.id.scrollable_top_appbar_transparent_status_bar_dest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_scrollable_top_appbar_transparent_status_bar, container, false
    )
}
