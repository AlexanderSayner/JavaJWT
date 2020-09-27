package org.sayner.student.jwtdemo.model

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name="employee",schema = "basic")
data class Employee(
        @Id
        @GeneratedValue(strategy= IDENTITY)
        var id:Int?,
        var firstname:String?,
        var lastname:String?,
        var state:String?,
        var email:String?,
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                schema="basic",
                name = "employee_and_role",
                joinColumns = [JoinColumn(name = "employee_id")],
                inverseJoinColumns = [JoinColumn(name = "employee_role_id")])
        var roles:Set<Role>,
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                schema="basic",
                name = "employee_department",
                joinColumns = [JoinColumn(name = "employee_id")],
                inverseJoinColumns = [JoinColumn(name = "department_id")])
        var departments:Set<Department>,
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                schema="basic",
                name = "employee_project",
                joinColumns = [JoinColumn(name = "employee_id")],
                inverseJoinColumns = [JoinColumn(name = "project_id")])
        var projects:Set<Project>
)
