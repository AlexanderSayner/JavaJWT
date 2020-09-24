package org.sayner.student.jwtdemo.dto

import java.time.LocalDate

data class StateDto (
    val state:String,
    val startDate : LocalDate,
    val endDate: LocalDate
    )
