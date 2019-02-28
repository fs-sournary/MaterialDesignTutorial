package com.sournary.materialdesigntutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.sournary.materialdesigntutorial.extension.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private var navControllerListener: NavController.OnDestinationChangedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupNavigation()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        val controller = bottomNav.setupWithNavController(
            navGraphIds = listOf(
                R.navigation.nav_component,
                R.navigation.nav_animation,
                R.navigation.nav_icon_animation
            ),
            fragmentManager = supportFragmentManager,
            containerId = R.id.mainContainer,
            intent = intent
        ).apply { currentNavController = this }
        controller.observe(this, Observer {
            setupActionBarWithNavController(it)
            setupBottomBavWithNavController(it)
        })
    }

    private fun setupBottomBavWithNavController(navController: NavController) {
        navControllerListener?.let {
            navController.removeOnDestinationChangedListener(it)
            navControllerListener = null
        }
        navControllerListener = NavController.OnDestinationChangedListener { _, dest, _ ->
            when (dest.id) {
                R.id.component_menu_dest, R.id.animation_menu_dest, R.id.icon_animation_dest -> {
                    bottomNav.isVisible = true
                    toolbar.isVisible = true
                }
                R.id.scrollable_top_appbar_transparent_status_bar_dest -> {
                    bottomNav.isVisible = false
                    toolbar.isVisible = false
                }
                else -> {
                    bottomNav.isVisible = false
                    toolbar.isVisible = true
                }
            }
        }.apply { navController.addOnDestinationChangedListener(this) }
    }

    override fun onSupportNavigateUp(): Boolean = currentNavController?.value?.navigateUp() ?: false
}
