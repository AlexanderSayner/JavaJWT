package org.sayner.student.jwtdemo.controller

import io.swagger.v3.oas.annotations.Operation
import org.sayner.student.jwtdemo.dto.ClientDto
import org.sayner.student.jwtdemo.model.Client
import org.sayner.student.jwtdemo.service.ClientService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/client")
class ClientController(
        private val clientService: ClientService
) {
    @Operation(summary = "Get all records from client table")
    @GetMapping
    fun read(): ResponseEntity<List<Client>> =
            ResponseEntity(clientService.findAll(), HttpStatus.OK)

    @Operation(summary = "Create one more client")
    @PostMapping
    fun create(@RequestBody clientDto: ClientDto): ResponseEntity<Client> =
            ResponseEntity(clientService.create(clientDto), HttpStatus.OK)

    @Operation(summary = "Update client")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody clientDto: ClientDto): ResponseEntity<Client> =
            ResponseEntity(clientService.update(id, clientDto), HttpStatus.OK)

    @Operation(summary = "Delete client")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        clientService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}
