package org.sayner.student.jwtdemo.dto

import java.time.LocalDate

data class ProjectDto(
        val name: String,
        val open_date: LocalDate,
        val release_date: LocalDate
)
