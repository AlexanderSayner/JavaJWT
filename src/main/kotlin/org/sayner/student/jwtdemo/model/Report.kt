package org.sayner.student.jwtdemo.model
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name="report",schema = "basic")
data class Report(
        @Id
        @GeneratedValue(strategy= IDENTITY)
        var id:Int?,
        var topic:String?,
        var message:String?,
        @ManyToOne
        @JoinColumn(name = "client_id", nullable = false)
        var client: Client
)
