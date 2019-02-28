package com.sournary.materialdesigntutorial.ui.fab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_fab.*

/**
 * Created: 22/08/2018
 * By: Sang
 * Description:
 */
class FabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fab, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fab_state_button.setOnClickListener {
            if (fab.isShown) fab.hide() else fab.show()
            if (extended_fab.isShown) extended_fab.hide() else extended_fab.show()
        }
    }
}
