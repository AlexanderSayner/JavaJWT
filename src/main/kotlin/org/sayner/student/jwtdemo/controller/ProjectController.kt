package org.sayner.student.jwtdemo.controller

import io.swagger.v3.oas.annotations.Operation
import org.sayner.student.jwtdemo.dto.ProjectDto
import org.sayner.student.jwtdemo.model.Project
import org.sayner.student.jwtdemo.service.ProjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/project")
class ProjectController(
        private val projectService: ProjectService
) {
    @Operation(summary = "Get all records from project table")
    @GetMapping
    fun read(): ResponseEntity<List<Project>> =
            ResponseEntity(projectService.findAll(), HttpStatus.OK)

    @Operation(summary = "Create one more project")
    @PostMapping
    fun create(@RequestBody projectDto: ProjectDto): ResponseEntity<Project> =
            ResponseEntity(projectService.create(projectDto), HttpStatus.OK)

    @Operation(summary = "Update project")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody projectDto: ProjectDto): ResponseEntity<Project> =
            ResponseEntity(projectService.update(id, projectDto), HttpStatus.OK)

    @Operation(summary = "Delete project")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        projectService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}