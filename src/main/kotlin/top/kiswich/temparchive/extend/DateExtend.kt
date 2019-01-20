package top.kiswich.temparchive.extend

import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

data class DateBean(
    val year: Int,
    val month: Int,
    val day: Int
)

fun Date.getTodayTime(): DateBean {
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = this
    return DateBean(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH) + 1,
        calendar.get(Calendar.DAY_OF_MONTH)
    )
}

