package org.sayner.student.jwtdemo.repository

import org.sayner.student.jwtdemo.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee,Int>
