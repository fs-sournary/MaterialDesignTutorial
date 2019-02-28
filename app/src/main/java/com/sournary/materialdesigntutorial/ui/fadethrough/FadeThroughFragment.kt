package com.sournary.materialdesigntutorial.ui.fadethrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.transition.fadeThrough
import kotlinx.android.synthetic.main.fragment_fade_through.*

/**
 * 1, Created in 10/8/19 by Sang
 * 2, Description:
 */
class FadeThroughFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fade_through, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fadeThrough = fadeThrough()
        sceneButton.setOnClickListener {
            if (firstScene.isVisible) {
                TransitionManager.beginDelayedTransition(card, fadeThrough)
                firstScene.isVisible = false
                secondScene.isVisible = true
                sceneButton.text = getString(R.string.fade_through_scene_1)
            } else {
                TransitionManager.beginDelayedTransition(card, fadeThrough)
                secondScene.isVisible = false
                firstScene.isVisible = true
                sceneButton.text = getString(R.string.fade_through_scene_2)
            }
        }
    }
}
