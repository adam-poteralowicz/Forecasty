package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Alert(
    val senderName: String,
    val event: String,
    val start: Int,
    val end: Int,
    val description: String,
) : Parcelable
