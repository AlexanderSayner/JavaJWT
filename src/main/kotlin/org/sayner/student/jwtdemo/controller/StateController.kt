package org.sayner.student.jwtdemo.controller

import io.swagger.v3.oas.annotations.Operation
import org.sayner.student.jwtdemo.dto.StateDto
import org.sayner.student.jwtdemo.dto.id.StateIdDto
import org.sayner.student.jwtdemo.model.State
import org.sayner.student.jwtdemo.service.StateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/state")
class StateController(
        private val stateService: StateService
) {
    @Operation(summary = "Get all records from state table")
    @GetMapping
    fun read(): ResponseEntity<List<State>> =
            ResponseEntity(stateService.findAll(), HttpStatus.OK)

    @Operation(summary = "Create one more state")
    @PostMapping("/{projectId}")
    fun create(@PathVariable projectId: Int, @RequestBody stateDto: StateDto): ResponseEntity<State> =
            ResponseEntity(stateService.create(projectId, stateDto), HttpStatus.OK)

    @Operation(summary = "Update state")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody stateDto: StateDto): ResponseEntity<State> =
            ResponseEntity(stateService.update(id, stateDto), HttpStatus.OK)

    @Operation(summary = "Delete state")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        stateService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "Getting project states")
    @GetMapping("/project/{id}")
    fun getProjectState(@PathVariable id: Int): ResponseEntity<List<StateIdDto>> =
            ResponseEntity(stateService.getProjectStates(id), HttpStatus.OK)
}