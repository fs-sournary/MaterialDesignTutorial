package com.sournary.materialdesigntutorial.ui.datepicker

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.extension.showSnackbar
import kotlinx.android.synthetic.main.fragment_date_picker.*
import java.util.*

/**
 * Created in 9/23/19 by Sang
 * Description:
 */
class DatePickerFragment : Fragment() {

    private var today = 0L
    private var nextMonth = 0L
    private var january = 0L
    private var december = 0L
    private var oneYearForward = 0L
    private var beginMonthDay = 0L
    private var todayPair = Pair<Long, Long>(0, 0)
    private var nextMonthPair = Pair<Long, Long>(0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_date_picker, container, false)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_date_picker, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_date_picker_action -> showDatePicker()
            R.id.setting_dest -> showSnackbar("Show setting")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        today = calendar.timeInMillis
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        beginMonthDay = calendar.timeInMillis

        calendar.add(Calendar.MONTH, 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        nextMonth = calendar.timeInMillis

        calendar.set(Calendar.MONTH, Calendar.JANUARY)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        january = calendar.timeInMillis

        calendar.set(Calendar.MONTH, Calendar.DECEMBER)
        december = calendar.timeInMillis

        calendar.timeInMillis = today
        calendar.add(Calendar.YEAR, 1)
        oneYearForward = calendar.timeInMillis

        todayPair = Pair(today, today)
        nextMonthPair = Pair(nextMonth, nextMonth)

        when (selection_mode_radio_group.checkedRadioButtonId) {
            R.id.single_date_selection_button -> {
                val picker = createSingleDateSelectionPicker()
                picker.show(fragmentManager ?: return, picker.toString())
            }
            R.id.date_range_selection_button -> {
                val picker = createDateRangeSelectionPicker()
                picker.show(fragmentManager ?: return, picker.toString())
            }
        }
    }

    private fun createSingleDateSelectionPicker(): MaterialDatePicker<Long> {
        val datePickerBuilder = MaterialDatePicker.Builder.datePicker()
        when (selection_radio_group.checkedRadioButtonId) {
            R.id.today_selection_button -> datePickerBuilder.setSelection(today)
            R.id.next_month_selection_button -> datePickerBuilder.setSelection(nextMonth)
        }
        when (theme_radio_group.checkedRadioButtonId) {
            R.id.dialog_theme_button -> datePickerBuilder.setTheme(R.style.ThemeOverlay_MaterialTutorial_MaterialCalendar)
            R.id.fullscreen_theme_button -> datePickerBuilder.setTheme(R.style.ThemeOverlay_MaterialTutorial_MaterialCalendar_Fullscreen)
        }
        if (picker_title_radio_group.checkedRadioButtonId == R.id.custom_title_button) {
            datePickerBuilder.setTitleText(R.string.date_picker_title)
        }
        val constraintBuilder = CalendarConstraints.Builder()
        when (bounds_radio_group.checkedRadioButtonId) {
            R.id.this_year_bounds_button -> {
                constraintBuilder.setStart(january)
                constraintBuilder.setEnd(december)
            }
            R.id.within_one_year_bounds_button -> {
                constraintBuilder.setStart(beginMonthDay)
                constraintBuilder.setEnd(oneYearForward)
            }
        }
        when (valid_day_radio_group.checkedRadioButtonId) {
            R.id.today_onward_valid_button -> {
                constraintBuilder.setValidator(DateValidatorPointForward.from(today))
            }
            R.id.weekdays_valid_action -> {
                constraintBuilder.setValidator(DateValidatorWeekDays())
            }
        }
        when (opening_radio_group.checkedRadioButtonId) {
            R.id.today_opening_button -> constraintBuilder.setOpenAt(today)
            R.id.next_month_opening_button -> constraintBuilder.setOpenAt(nextMonth)
        }
        datePickerBuilder.setCalendarConstraints(constraintBuilder.build())
        return datePickerBuilder.build().apply {
            addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = it
                val time =
                    "${calendar.get(Calendar.DAY_OF_MONTH)} - ${calendar.get(Calendar.MONTH)}"
                Toast.makeText(context!!, time, Toast.LENGTH_SHORT).show()
                Snackbar.make(view!!, "Positive button", Snackbar.LENGTH_INDEFINITE).show()
            }
            addOnNegativeButtonClickListener {
                Toast.makeText(context!!, "You have clicked to cancel button", Toast.LENGTH_SHORT)
                    .show()
            }
            addOnCancelListener {
                Toast.makeText(
                    context!!,
                    "You have clicked to back button or touch outside dialog",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun createDateRangeSelectionPicker(): MaterialDatePicker<Pair<Long, Long>> {
        val datePickerBuilder = MaterialDatePicker.Builder.dateRangePicker()
        when (selection_radio_group.checkedRadioButtonId) {
            R.id.today_selection_button -> datePickerBuilder.setSelection(todayPair)
            R.id.next_month_selection_button -> datePickerBuilder.setSelection(nextMonthPair)
        }
        when (theme_radio_group.checkedRadioButtonId) {
            R.id.dialog_theme_button -> datePickerBuilder.setTheme(R.style.ThemeOverlay_MaterialTutorial_MaterialCalendar)
            R.id.fullscreen_theme_button -> datePickerBuilder.setTheme(R.style.ThemeOverlay_MaterialTutorial_MaterialCalendar_Fullscreen)
        }
        if (picker_title_radio_group.checkedRadioButtonId == R.id.custom_title_button) {
            datePickerBuilder.setTitleText(R.string.date_picker_title)
        }
        val constraintBuilder = CalendarConstraints.Builder()
        when (bounds_radio_group.checkedRadioButtonId) {
            R.id.this_year_bounds_button -> {
                constraintBuilder.setStart(january)
                constraintBuilder.setEnd(december)
            }
            R.id.within_one_year_bounds_button -> {
                constraintBuilder.setStart(today)
                constraintBuilder.setEnd(oneYearForward)
            }
        }
        when (valid_day_radio_group.checkedRadioButtonId) {
            R.id.today_onward_valid_button -> {
                constraintBuilder.setValidator(DateValidatorPointForward.from(today))
            }
            R.id.weekdays_valid_action -> {
                constraintBuilder.setValidator(DateValidatorWeekDays())
            }
        }
        when (opening_radio_group.checkedRadioButtonId) {
            R.id.today_opening_button -> constraintBuilder.setOpenAt(today)
            R.id.next_month_opening_button -> constraintBuilder.setOpenAt(nextMonth)
        }
        datePickerBuilder.setCalendarConstraints(constraintBuilder.build())
        return datePickerBuilder.build().apply {
            addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = it.first ?: return@addOnPositiveButtonClickListener
                val timeFrom =
                    "${calendar.get(Calendar.DAY_OF_MONTH)} - ${calendar.get(Calendar.MONTH)}"
                calendar.timeInMillis = it.second ?: return@addOnPositiveButtonClickListener
                val timeTo =
                    "${calendar.get(Calendar.DAY_OF_MONTH)} - ${calendar.get(Calendar.MONTH)}"
                Toast.makeText(context!!, "$timeFrom and $timeTo", Toast.LENGTH_SHORT).show()
            }
            addOnNegativeButtonClickListener {
                Toast.makeText(context!!, "You have clicked to cancel button", Toast.LENGTH_SHORT)
                    .show()
            }
            addOnCancelListener {
                Toast.makeText(
                    context!!,
                    "You have clicked to back button or touch outside dialog",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
