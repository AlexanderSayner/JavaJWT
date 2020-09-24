package org.sayner.student.jwtdemo.model

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "employee_role", schema = "basic")
data class Role(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Int?,
        var role: String?,
        var skillLevel: String?,
        @ManyToMany(mappedBy = "roles")
        var employees:Set<Employee>
)
