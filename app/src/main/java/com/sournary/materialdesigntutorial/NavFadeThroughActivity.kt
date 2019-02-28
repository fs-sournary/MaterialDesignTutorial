package com.sournary.materialdesigntutorial

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sournary.materialdesigntutorial.util.EdgeToEdge
import kotlinx.android.synthetic.main.activity_nav_fade_through.*

/**
 * Created in 15/10/2019 by Sang
 */
class NavFadeThroughActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_fade_through)
        EdgeToEdge.setupRoot(findViewById(R.id.container))
        window.sharedElementExitTransition
    }
}
