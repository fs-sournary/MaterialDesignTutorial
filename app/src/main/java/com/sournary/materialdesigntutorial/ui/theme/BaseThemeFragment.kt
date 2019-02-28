package com.sournary.materialdesigntutorial.ui.theme

import android.os.Bundle
import android.view.*
import android.widget.ScrollView
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.extension.navigateToDestAndClearCurrent
import com.sournary.materialdesigntutorial.extension.showSnackbar
import kotlinx.android.synthetic.main.layout_shape_theme_content.*

/**
 * Created in 9/23/19 by Sang
 * Description:
 */
abstract class BaseThemeFragment : Fragment() {

    private val viewModel: ShapeThemeViewModel by activityViewModels()

    //    private var statusBarColor: Int? = null
    private lateinit var themeWrapper: ContextThemeWrapper
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        themeWrapper = ContextThemeWrapper(context, getTheme())
        val layoutInflaterWithThemeWrapper = inflater.cloneInContext(themeWrapper)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val window = activity?.window ?: return null
//            statusBarColor = window.statusBarColor
//            val typeValue = TypedValue()
//            themeWrapper.theme.resolveAttribute(R.attr.colorPrimaryDark, typeValue, true)
//            window.statusBarColor = typeValue.data
//        }
        val rootView = layoutInflaterWithThemeWrapper.inflate(getLayoutId(), container, false)
        val groupView = rootView.findViewById<ScrollView>(R.id.container)
        layoutInflaterWithThemeWrapper.inflate(R.layout.layout_shape_theme_content, groupView, true)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.shapeThemeDestPosition = 0
            navController.popBackStack()
        }
        first_text_input.setOnKeyListener { _, _, _ ->
            val input = first_text_input.text.toString()
            if (input.length < 4) {
                first_input_layout.error = "An unexpected error"
            } else {
                first_input_layout.error = null
            }
            false
        }
        show_dialog_button.setOnClickListener {
            MaterialAlertDialogBuilder(context ?: return@setOnClickListener, getTheme())
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("OK", null)
                .setCancelable(false)
                // .setNegativeButton(getString(R.string.negative), null)
                // .setNeutralButton(getString(R.string.neutral), null)
                .show()
        }
        val bottomSheet = BottomSheetDialog(themeWrapper).apply {
            setContentView(R.layout.layout_theme_bottomsheet_content)
        }
        val bottomSheetInternal = bottomSheet.findViewById<View>(
            R.id.design_bottom_sheet
        ) ?: return
        BottomSheetBehavior.from(bottomSheetInternal).peekHeight = 300
        show_bottom_sheet_button.setOnClickListener { bottomSheet.show() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_shape_theme, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                viewModel.shapeThemeDestPosition = 0
                navController.popBackStack()
            }
            R.id.shape_theme_type_action -> showShapeThemeTypeDialog()
            R.id.setting_dest -> showSnackbar("Show setting")
            else -> showSnackbar("Unknown action")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showShapeThemeTypeDialog() {
        var position = -1
        val choiceItems = listOf("Crane", "Fort nightly", "Shrine").toTypedArray()
        MaterialAlertDialogBuilder(context ?: return)
            .setTitle("Choose shape theme type")
            .setSingleChoiceItems(choiceItems, viewModel.shapeThemeDestPosition) { _, which ->
                position = which
            }
            .setPositiveButton(getString(R.string.positive)) { _, _ ->
                if (viewModel.shapeThemeDestPosition == position || position == -1) return@setPositiveButton
                when (position) {
                    0 -> navigateToDestAndClearCurrent(
                        getCurrentDestId(), R.id.crane_theme_dest
                    )
                    1 -> navigateToDestAndClearCurrent(
                        getCurrentDestId(), R.id.fort_nightly_theme_dest
                    )
                    2 -> navigateToDestAndClearCurrent(
                        getCurrentDestId(), R.id.shrine_theme_dest
                    )
                    else -> showSnackbar("Unknown dest")
                }
                viewModel.shapeThemeDestPosition = position
            }
            .setNegativeButton(getString(R.string.negative), null)
            .show()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    @StyleRes
    abstract fun getTheme(): Int

    @IdRes
    abstract fun getCurrentDestId(): Int
}
