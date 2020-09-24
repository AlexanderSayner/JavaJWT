package org.sayner.student.jwtdemo.repository

import org.sayner.student.jwtdemo.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository :JpaRepository<Role,Int>
