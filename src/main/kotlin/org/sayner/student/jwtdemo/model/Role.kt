package org.sayner.student.jwtdemo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "employee_role", schema = "basic")
data class Role(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Int?,
        var role: String?,
        var skillLevel: String?
)
