package org.sayner.student.jwtdemo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="department",schema = "basic")
data class Department(
        @Id
        @GeneratedValue(strategy= IDENTITY)
        var id:Int?,
        var name:String?
)
