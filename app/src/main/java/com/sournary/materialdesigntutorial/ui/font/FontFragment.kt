package com.sournary.materialdesigntutorial.ui.font

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.data.Font
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 24/08/2018
 * By: Sang
 * Description:
 */
const val DEMO_FONT_DEFAULT = "Required Api 21"
const val STYLE_FONT_DEFAULT = android.R.style.TextAppearance_DeviceDefault

class FontFragment : BaseFragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_font, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupFontList()
    }

    private fun setupToolbar() {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) { title = "Font" }
    }

    private fun setupFontList() {
        val fontRecyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_font)
        activity?.let {
            val fontAdapter = FontAdapter(it)
            fontRecyclerView.adapter = fontAdapter
            fontAdapter.submitList(makeFontList())
        }
    }

    @SuppressLint("InlinedApi")
    private fun makeFontList(): List<Font> {
        val fonts = mutableListOf<Font>()

        val headLineFont = Font(
            name = "Head line",
            describe = "Head line Font by Material component",
            demo = getFontDemo("Header line"),
            style = getFontStyle(android.R.style.TextAppearance_Material_Headline)
        )
        fonts.add(headLineFont)

        val subHead = Font(
            name = "SubHead",
            describe = "Subtitle Font by Material component",
            demo = getFontDemo("Subtitle"),
            style = getFontStyle(android.R.style.TextAppearance_Material_Subhead)
        )
        fonts.add(subHead)

        val body1 = Font(
            name = "Body 1",
            describe = "Subtitle Font by Material component",
            demo = getFontDemo("Body 1"),
            style = getFontStyle(android.R.style.TextAppearance_Material_Body1)
        )
        fonts.add(body1)

        val body2 = Font(
            name = "Body 2",
            describe = "Subtitle Font by Material component",
            demo = getFontDemo("Body 2"),
            style = getFontStyle(android.R.style.TextAppearance_Material_Body2)
        )
        fonts.add(body2)

        val buttonText = Font(
            name = "Button Text",
            describe = "Subtitle Font by Material component",
            demo = getFontDemo("Button text"),
            style = getFontStyle(android.R.style.TextAppearance_Material_Button)
        )
        fonts.add(buttonText)

        return fonts
    }

    private fun getFontDemo(demo: String): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            demo
        } else {
            DEMO_FONT_DEFAULT
        }

    private fun getFontStyle(@StyleRes demoFontId: Int): Int =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            demoFontId
        } else {
            STYLE_FONT_DEFAULT
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> NavHostFragment.findNavController(this).popBackStack()
        }
        return true
    }

    override fun onBackPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }
}