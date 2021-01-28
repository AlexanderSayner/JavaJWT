package org.sayner.student.jwtdemo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "report", schema = "basic")
data class Report(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Int?,
    var topic: String?,
    var message: String?,
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    var client: Client
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Report

        if (id != other.id) return false
        if (topic != other.topic) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (topic?.hashCode() ?: 0)
        result = 31 * result + (message?.hashCode() ?: 0)
        return result
    }
}
