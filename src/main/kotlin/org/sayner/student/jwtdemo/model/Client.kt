package org.sayner.student.jwtdemo.model

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "client", schema = "basic")
data class Client(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        var id: Int?,
        var username: String?,
        var state: String?,
        var email: String,
        @OneToMany(
                mappedBy = "client",
                cascade = [CascadeType.ALL],
                fetch = FetchType.EAGER,
                orphanRemoval = true)
        var reports: Set<Report>
)
