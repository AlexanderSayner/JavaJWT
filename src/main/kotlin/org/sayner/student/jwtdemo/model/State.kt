package org.sayner.student.jwtdemo.model

import java.time.LocalDate
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "project_state", schema = "basic")
data class State(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Int?,
        var state:String?,
        var startDate : LocalDate?,
        var endDate:LocalDate?,
        @ManyToOne
        @JoinColumn(name = "project_id", nullable = false)
        var project: Project?
)
