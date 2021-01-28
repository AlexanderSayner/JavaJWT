package org.sayner.student.jwtdemo.repository

import org.sayner.student.jwtdemo.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Int>
