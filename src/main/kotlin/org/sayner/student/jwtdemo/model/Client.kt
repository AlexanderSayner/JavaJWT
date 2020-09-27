package org.sayner.student.jwtdemo.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
                cascade = [CascadeType.PERSIST],
                fetch = FetchType.LAZY,
                orphanRemoval = true)
        @JsonIgnore
        var reports: Set<Report>
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Client

                if (id != other.id) return false
                if (username != other.username) return false
                if (state != other.state) return false
                if (email != other.email) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id ?: 0
                result = 31 * result + (username?.hashCode() ?: 0)
                result = 31 * result + (state?.hashCode() ?: 0)
                result = 31 * result + email.hashCode()
                return result
        }
}
