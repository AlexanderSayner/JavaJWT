package org.sayner.student.jwtdemo.controller

import io.swagger.v3.oas.annotations.Operation
import org.sayner.student.jwtdemo.dto.EmployeeDto
import org.sayner.student.jwtdemo.model.Employee
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
    fun update(@PathVariable id:Int, @RequestBody employeeDto: EmployeeDto):ResponseEntity<Employee> =
            ResponseEntity(employeeService.updateEmployee(id,employeeDto),OK)

    @Operation(summary = "Delete employee")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Int):ResponseEntity<Any> {
        employeeService.deleteEmployee(id)
        return ResponseEntity(OK)
    }
}
