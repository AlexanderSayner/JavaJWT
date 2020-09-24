package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.EmployeeDto
import org.sayner.student.jwtdemo.model.Employee
import org.sayner.student.jwtdemo.repository.EmployeeRepository
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {
    private val logger = getLogger(EmployeeService::class.java)

    fun findAll(): List<Employee> = employeeRepository.findAll()

    fun createEmployee(employeeDto: EmployeeDto): Employee {
        val newEmployee = Employee(
                null,
                employeeDto.firstname,
                employeeDto.lastname,
                employeeDto.state,
                employeeDto.email,
                emptySet(),
                emptySet(),
                emptySet()
        )
        logger.info("Saving new employee $newEmployee")
        return employeeRepository.save(newEmployee)
    }

    fun updateEmployee(id: Int, employeeDto: EmployeeDto): Employee {
        val optional = employeeRepository.findById(id)
        if (optional.isPresent) {
            val employee = optional.get()
            employee.firstname = employeeDto.firstname
            employee.lastname = employeeDto.lastname
            employee.state = employeeDto.state
            employee.email = employeeDto.email
            logger.info("Updating employee $employee")
            return employeeRepository.save(employee)
        }
        logger.error("Employee with id=$id not found")
        throw IllegalStateException("I can't find employee with id=$id")
    }

    fun deleteEmployee(id: Int) {
        employeeRepository.deleteById(id)
        logger.info("Employee $id deleted")
    }
}
