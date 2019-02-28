package com.sournary.materialdesigntutorial.ui.datepicker

import com.google.android.material.datepicker.CalendarConstraints
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created in 9/26/19 by Sang
 * Description:
 */
@Parcelize
class DateValidatorWeekDays : CalendarConstraints.DateValidator {

    override fun isValid(date: Long): Boolean {
        val utcCalendar = Calendar.getInstance()
        utcCalendar.timeInMillis = date
        val dayOfWeek = utcCalendar.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY
    }
}
