package org.sayner.student.jwtdemo.model

import java.time.LocalDate
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "project", schema = "basic")
data class Project(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Int?,
        var name: String?,
        var open_date: LocalDate?,
        var release_date: LocalDate?,
        @OneToMany(
                mappedBy = "project",
                cascade = [CascadeType.ALL],
                fetch = FetchType.EAGER,
                orphanRemoval = true)
        var states: Set<State>,
        @ManyToMany(mappedBy = "projects")
        var employees:Set<Employee>
)
