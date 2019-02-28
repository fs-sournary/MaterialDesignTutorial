package com.sournary.materialdesigntutorial.ui.tickcross

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_tick_cross.*

/**
 * Created in 24/10/2019 by Sang
 */
class TickCrossFragment : Fragment() {

    private val viewModel: TickCrossViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tick_cross, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val tickToCrossAvd =
            context!!.getDrawable(R.drawable.avd_tick_to_cross) as AnimatedVectorDrawable
        val crossToTickAvd =
            context!!.getDrawable(R.drawable.avd_cross_to_tick) as AnimatedVectorDrawable
        tick_cross_image.setOnClickListener {
            val avd = if (viewModel.tick) tickToCrossAvd else crossToTickAvd
            tick_cross_image.setImageDrawable(avd)
            avd.start()
            viewModel.tick = !viewModel.tick
        }
    }
}
