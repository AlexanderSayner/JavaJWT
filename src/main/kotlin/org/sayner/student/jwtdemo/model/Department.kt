package org.sayner.student.jwtdemo.model

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name="department",schema = "basic")
data class Department(
        @Id
        @GeneratedValue(strategy= IDENTITY)
        var id:Int?,
        var name:String?,
        @ManyToMany(mappedBy = "departments")
        var employees:Set<Employee>
)
