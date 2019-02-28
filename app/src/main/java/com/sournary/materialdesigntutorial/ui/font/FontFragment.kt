package com.sournary.materialdesigntutorial.ui.font

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Font
import kotlinx.android.synthetic.main.fragment_font.*

/**
 * Created: 24/08/2018
 * By: Sang
 * Description:
 */
class FontFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?  = inflater.inflate(R.layout.fragment_font, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupFontList()
    }

    private fun setupFontList() {
        activity?.let {
            val fontAdapter = FontAdapter(it)
            recycler_font.adapter = fontAdapter
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

    companion object{

        const val DEMO_FONT_DEFAULT = "Required Api 21"
        const val STYLE_FONT_DEFAULT = android.R.style.TextAppearance_DeviceDefault
    }
}
