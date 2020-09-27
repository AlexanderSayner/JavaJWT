package org.sayner.student.jwtdemo.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
                orphanRemoval = true)
        @JsonIgnore
        var states: Set<State>
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Project

                if (name != other.name) return false
                if (open_date != other.open_date) return false
                if (release_date != other.release_date) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id ?: 0
                result = 31 * result + (name?.hashCode() ?: 0)
                result = 31 * result + (open_date?.hashCode() ?: 0)
                result = 31 * result + (release_date?.hashCode() ?: 0)
                return result
        }
}
