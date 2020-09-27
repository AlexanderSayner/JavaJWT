package org.sayner.student.jwtdemo.controller

import io.swagger.v3.oas.annotations.Operation
import org.sayner.student.jwtdemo.dto.ReportDto
import org.sayner.student.jwtdemo.model.Report
import org.sayner.student.jwtdemo.service.ReportService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/report")
class ReportController(
        private val reportService: ReportService
) {
    @Operation(summary = "Get all reports from state table")
    @GetMapping
    fun read(): ResponseEntity<List<Report>> =
            ResponseEntity(reportService.findAll(), HttpStatus.OK)

    @Operation(summary = "Create one more report")
    @PostMapping("/{clientId}")
    fun create(@PathVariable clientId: Int, @RequestBody dto: ReportDto): ResponseEntity<Report> =
            ResponseEntity(reportService.create(clientId, dto), HttpStatus.OK)

    @Operation(summary = "Update report")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody reportDto: ReportDto): ResponseEntity<Report> =
            ResponseEntity(reportService.update(id, reportDto), HttpStatus.OK)

    @Operation(summary = "Delete report")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        reportService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "Getting clients reports")
    @GetMapping("/client/{id}")
    fun getClientReports(@PathVariable id: Int): ResponseEntity<Collection<Report>> =
            ResponseEntity(reportService.getClientReports(id), HttpStatus.OK)
}
