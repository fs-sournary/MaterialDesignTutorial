package com.sournary.materialdesigntutorial.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sournary.materialdesigntutorial.SharedViewModel

/**
 * Created by fs-sournary.
 * Date: 8/18/18.
 * Description:
 */
abstract class BaseFragment : Fragment() {

    lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.let {
            sharedViewModel = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.backPressEvent.observe(this, Observer {
            onBackPress()
        })
    }

    abstract fun onBackPress()
}