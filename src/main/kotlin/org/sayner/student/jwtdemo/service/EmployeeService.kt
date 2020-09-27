package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.EmployeeDto
import org.sayner.student.jwtdemo.model.Department
import org.sayner.student.jwtdemo.model.Employee
import org.sayner.student.jwtdemo.model.Project
import org.sayner.student.jwtdemo.model.Role
import org.sayner.student.jwtdemo.repository.DepartmentRepository
import org.sayner.student.jwtdemo.repository.EmployeeRepository
import org.sayner.student.jwtdemo.repository.ProjectRepository
import org.sayner.student.jwtdemo.repository.RoleRepository
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Service

@Service
class EmployeeService(
        private val employeeRepository: EmployeeRepository,
        private val projectRepository: ProjectRepository,
        private val roleRepository: RoleRepository,
        private val departmentRepository: DepartmentRepository
) {
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
            logger.info("Updating employee ${employee.id}")
            return employeeRepository.save(employee)
        }
        logger.error("Employee with id=$id not found")
        throw IllegalStateException("I can't find employee with id=$id")
    }

    fun deleteEmployee(id: Int) {
        employeeRepository.deleteById(id)
        logger.info("Employee $id deleted")
    }

    fun bindEmployeeProject(employeeId: Int, projectId: Int) {
        val employee = getEmployee(employeeId)
        val projectOptional = projectRepository.findById(projectId)
        if (projectOptional.isEmpty) {
            throw IllegalStateException("I can't find project with id=$projectId")
        }
        val project = projectOptional.get()
        employee.projects = employee.projects.plusElement(project)
        employeeRepository.save(employee)
        logger.info("Employee ${employee.id} now in project ${project.id}")
    }

    fun unbindEmployeeProject(employeeId: Int, projectId: Int) {
        val employee = getEmployee(employeeId)
        val projectOptional =
                employee.projects
                        .stream()
                        .filter { t -> t.id == projectId }
                        .findFirst()
        if (projectOptional.isEmpty) {
            throw IllegalStateException("Employee has not project with id=$projectId")
        }
        val project = projectOptional.get()
        employee.projects = employee.projects.minusElement(project)
        employeeRepository.save(employee)
        logger.info("Employee ${employee.id} now not in project ${project.id}")
    }

    fun getEmployeeProjects(employeeId: Int): Set<Project> =
            getEmployee(employeeId).projects

    fun bindEmployeeRole(employeeId: Int, roleId: Int) {
        val employee = getEmployee(employeeId)
        val roleOptional = roleRepository.findById(roleId)
        if (roleOptional.isEmpty) {
            throw IllegalStateException("I can't find role with id=$roleId")
        }
        val role = roleOptional.get()
        employee.roles = employee.roles.plusElement(role)
        employeeRepository.save(employee)
        logger.info("Employee ${employee.id} now has role ${role.id}")
    }

    fun unbindEmployeeRole(employeeId: Int, roleId: Int) {
        val employee = getEmployee(employeeId)
        val roleOptional =
                employee.roles
                        .stream()
                        .filter { t -> t.id == roleId }
                        .findFirst()
        if (roleOptional.isEmpty) {
            throw IllegalStateException("Employee has not got role with id=$roleId")
        }
        val role = roleOptional.get()
        employee.roles = employee.roles.minusElement(role)
        employeeRepository.save(employee)
        logger.info("Employee ${employee.id} now has not role ${role.id}")
    }

    fun getEmployeeRoles(employeeId: Int): Set<Role> =
            getEmployee(employeeId).roles

    fun bindEmployeeDepartment(employeeId: Int, departmentId: Int) {
        val employee = getEmployee(employeeId)
        val departmentOptional = departmentRepository.findById(departmentId)
        if (departmentOptional.isEmpty) {
            throw IllegalStateException("I can't find department with id=$departmentId")
        }
        val department = departmentOptional.get()
        employee.departments = employee.departments.plusElement(department)
        employeeRepository.save(employee)
        logger.info("Employee ${employee.id} now in department ${department.id}")
    }

    fun unbindEmployeeDepartment(employeeId: Int, departmentId: Int) {
        val employee = getEmployee(employeeId)
        val departmentOptional =
                employee.departments
                        .stream()
                        .filter { t -> t.id == departmentId }
                        .findFirst()
        if (departmentOptional.isEmpty) {
            throw IllegalStateException("Employee is not in department with id=$departmentId")
        }
        val department = departmentOptional.get()
        employee.departments = employee.departments.minusElement(department)
        employeeRepository.save(employee)
        logger.info("Employee ${employee.id} now is not in department ${department.id}")
    }

    fun getEmployeeDepartment(employeeId: Int): Set<Department> =
            getEmployee(employeeId).departments

    private fun getEmployee(employeeId: Int): Employee {
        val employeeOptional = employeeRepository.findById(employeeId)
        if (employeeOptional.isEmpty) {
            throw IllegalStateException("I can't find employee with id=$employeeId")
        }
        return employeeOptional.get()
    }
}
