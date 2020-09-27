package org.sayner.student.jwtdemo.controller

import io.swagger.v3.oas.annotations.Operation
import org.sayner.student.jwtdemo.dto.RoleDto
import org.sayner.student.jwtdemo.model.Role
import org.sayner.student.jwtdemo.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/role")
class RoleController(
        private val roleService: RoleService
) {
    @Operation(summary = "Get all records from role table")
    @GetMapping
    fun read(): ResponseEntity<List<Role>> =
            ResponseEntity(roleService.findAll(), HttpStatus.OK)

    @Operation(summary = "Create one more role")
    @PostMapping
    fun create(@RequestBody roleDto: RoleDto): ResponseEntity<Role> =
            ResponseEntity(roleService.create(roleDto), HttpStatus.OK)

    @Operation(summary = "Update role")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody roleDto: RoleDto): ResponseEntity<Role> =
            ResponseEntity(roleService.update(id, roleDto), HttpStatus.OK)

    @Operation(summary = "Delete role")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        roleService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}