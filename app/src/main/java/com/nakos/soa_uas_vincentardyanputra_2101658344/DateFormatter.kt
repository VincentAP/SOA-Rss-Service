package com.nakos.soa_uas_vincentardyanputra_2101658344

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun pubDateFormat(date: String): String =
        SimpleDateFormat("MMM d, h:mm a", Locale.ENGLISH).format(
            SimpleDateFormat("E, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(date)
                ?: Calendar.getInstance().time
        )
}