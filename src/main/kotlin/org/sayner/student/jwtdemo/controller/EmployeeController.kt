package org.sayner.student.jwtdemo.controller

import io.swagger.v3.oas.annotations.Operation
import org.sayner.student.jwtdemo.dto.EmployeeDto
import org.sayner.student.jwtdemo.dto.request.BindEmployeeDto
import org.sayner.student.jwtdemo.model.Department
import org.sayner.student.jwtdemo.model.Employee
import org.sayner.student.jwtdemo.model.Project
import org.sayner.student.jwtdemo.model.Role
import org.sayner.student.jwtdemo.service.EmployeeService
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/employee")
class EmployeeController(
        private val employeeService: EmployeeService
) {
    @Operation(summary = "Get all records from employee table")
    @GetMapping
    fun read(): ResponseEntity<List<Employee>> =
            ResponseEntity(employeeService.findAll(), OK)

    @Operation(summary = "Create one more employee")
    @PostMapping
    fun create(@RequestBody employeeDto: EmployeeDto): ResponseEntity<Employee> =
            ResponseEntity(employeeService.createEmployee(employeeDto), OK)

    @Operation(summary = "Update employee")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody employeeDto: EmployeeDto): ResponseEntity<Employee> =
            ResponseEntity(employeeService.updateEmployee(id, employeeDto), OK)

    @Operation(summary = "Delete employee")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        employeeService.deleteEmployee(id)
        return ResponseEntity(OK)
    }

    @Operation(summary = "Bind project for employee")
    @PostMapping("/binding/project")
    fun bindEmployeeProject(@RequestBody bindEmployeeDto: BindEmployeeDto): ResponseEntity<Any> {
        employeeService.bindEmployeeProject(bindEmployeeDto.employeeId, bindEmployeeDto.bindId)
        return ResponseEntity(OK)
    }

    @Operation(summary = "Unleash project for employee")
    @PostMapping("/unleash/project")
    fun unbindEmployeeProject(@RequestBody bindEmployeeDto: BindEmployeeDto): ResponseEntity<Any> {
        employeeService.unbindEmployeeProject(bindEmployeeDto.employeeId, bindEmployeeDto.bindId)
        return ResponseEntity(OK)
    }

    @Operation(summary = "Get employee projects")
    @GetMapping("/project/{employeeId}")
    fun getEmployeeProjects(@PathVariable employeeId: Int): ResponseEntity<Set<Project>> =
            ResponseEntity(employeeService.getEmployeeProjects(employeeId), OK)

    @Operation(summary = "Bind employee role")
    @PostMapping("/binding/role")
    fun bindEmployeeRole(@RequestBody bindEmployeeDto: BindEmployeeDto): ResponseEntity<Any> {
        employeeService.bindEmployeeRole(bindEmployeeDto.employeeId, bindEmployeeDto.bindId)
        return ResponseEntity(OK)
    }

    @Operation(summary = "Unleash employee role")
    @PostMapping("/unleash/role")
    fun unbindEmployeeRole(@RequestBody bindEmployeeDto: BindEmployeeDto): ResponseEntity<Any> {
        employeeService.unbindEmployeeRole(bindEmployeeDto.employeeId, bindEmployeeDto.bindId)
        return ResponseEntity(OK)
    }

    @Operation(summary = "Get employee roles")
    @GetMapping("/role/{employeeId}")
    fun getEmployeeRoles(@PathVariable employeeId: Int): ResponseEntity<Set<Role>> =
            ResponseEntity(employeeService.getEmployeeRoles(employeeId), OK)

    @Operation(summary = "Bind employee and department")
    @PostMapping("/binding/department")
    fun bindEmployeeDepartment(@RequestBody bindEmployeeDto: BindEmployeeDto): ResponseEntity<Any> {
        employeeService.bindEmployeeDepartment(bindEmployeeDto.employeeId, bindEmployeeDto.bindId)
        return ResponseEntity(OK)
    }

    @Operation(summary = "Unleash employee department")
    @PostMapping("/unleash/department")
    fun unbindEmployeeDepartment(@RequestBody bindEmployeeDto: BindEmployeeDto): ResponseEntity<Any> {
        employeeService.unbindEmployeeDepartment(bindEmployeeDto.employeeId, bindEmployeeDto.bindId)
        return ResponseEntity(OK)
    }

    @Operation
    @GetMapping("department/{employeeId}")
    fun getEmployeeDepartment(@PathVariable employeeId: Int): ResponseEntity<Set<Department>> =
            ResponseEntity(employeeService.getEmployeeDepartment(employeeId), OK)
}
