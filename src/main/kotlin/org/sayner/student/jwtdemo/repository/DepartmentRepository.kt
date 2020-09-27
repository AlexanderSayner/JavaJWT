package org.sayner.student.jwtdemo.repository

import org.sayner.student.jwtdemo.model.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department, Int>
