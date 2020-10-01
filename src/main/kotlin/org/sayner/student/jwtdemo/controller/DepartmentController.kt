package org.sayner.student.jwtdemo.controller

import io.swagger.v3.oas.annotations.Operation
import org.sayner.student.jwtdemo.dto.DepartmentDto
import org.sayner.student.jwtdemo.dto.list.DepartmentListResponse
import org.sayner.student.jwtdemo.model.Department
import org.sayner.student.jwtdemo.service.DepartmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/department")
class DepartmentController(
        private val departmentService: DepartmentService
) {
    @Operation(summary = "Get all records from department table")
    @GetMapping
    fun read(): ResponseEntity<DepartmentListResponse> =
            ResponseEntity(DepartmentListResponse(departmentService.findAll()), HttpStatus.OK)

    @Operation(summary = "Create one more department")
    @PostMapping
    fun create(@RequestBody departmentDto: DepartmentDto): ResponseEntity<Department> =
            ResponseEntity(departmentService.create(departmentDto), HttpStatus.OK)

    @Operation(summary = "Update department")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody departmentDto: DepartmentDto): ResponseEntity<Department> =
            ResponseEntity(departmentService.update(id, departmentDto), HttpStatus.OK)

    @Operation(summary = "Delete department")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        departmentService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}
