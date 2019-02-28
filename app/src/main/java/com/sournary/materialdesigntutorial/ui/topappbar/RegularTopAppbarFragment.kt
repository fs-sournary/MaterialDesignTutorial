package com.sournary.materialdesigntutorial.ui.topappbar

import android.os.Bundle
import android.view.*
import com.sournary.materialdesigntutorial.R

/**
 * Created: 22/08/2018
 * By: Sang
 * Description:
 */
class RegularTopAppbarFragment : BaseTopAppbarFragment() {

    override fun getCurrentDestId(): Int = R.id.top_appbar_dest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_regular_top_appbar, container, false)
}
