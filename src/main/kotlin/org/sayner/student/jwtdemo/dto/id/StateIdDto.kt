package org.sayner.student.jwtdemo.dto.id

import java.time.LocalDate

data class StateIdDto(
        val id: Int,
        val state: String,
        val startDate: LocalDate,
        val endDate: LocalDate
)
