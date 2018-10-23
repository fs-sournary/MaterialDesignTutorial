package com.sournary.materialdesigntutorial.ui.list

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.transition.ChangeBounds
import androidx.transition.Explode
import androidx.transition.Slide
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment

/**
 * Created: 11/09/2018
 * By: Sang
 * Description:
 */
class ProductDetailFragment : BaseFragment() {

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enterTransition = Slide(Gravity.BOTTOM)
        sharedElementEnterTransition = ChangeBounds()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_product_detail, container, false)
        return rootView
    }

    override fun onBackPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }
}
