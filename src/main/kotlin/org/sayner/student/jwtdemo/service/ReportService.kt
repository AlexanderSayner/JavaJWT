package org.sayner.student.jwtdemo.service

import org.sayner.student.jwtdemo.dto.ReportDto
import org.sayner.student.jwtdemo.model.Report
import org.sayner.student.jwtdemo.repository.ClientRepository
import org.sayner.student.jwtdemo.repository.ReportRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ReportService(
        private val reportRepository: ReportRepository,
        private val clientRepository: ClientRepository
) {
    private val logger = LoggerFactory.getLogger(ReportService::class.java)

    fun findAll(): List<Report> = reportRepository.findAll()

    fun create(clientId: Int, reportDto: ReportDto): Report {
        val optional = clientRepository.findById(clientId)
        if (optional.isEmpty) {
            throw IllegalStateException("Нет клиента с id $clientId")
        }
        val report = Report(
                null,
                reportDto.topic,
                reportDto.message,
                optional.get()
        )
        logger.info("Saving new state $reportDto")
        return reportRepository.save(report)
    }

    fun update(id: Int, reportDto: ReportDto): Report {
        val optional = reportRepository.findById(id)
        if (optional.isPresent) {
            val report = optional.get()
            report.topic = reportDto.topic
            report.message = reportDto.message
            logger.info("Updating state ${report.id}")
            return reportRepository.save(report)
        }
        logger.error("Report with id=$id not found")
        throw IllegalStateException("I can't find report with id=$id")
    }

    fun delete(id: Int) {
        reportRepository.deleteById(id)
        logger.info("Report $id deleted")
    }

    fun getClientReports(clientId: Int): Collection<Report> {
        logger.info("Getting project states")
        val opt = clientRepository.findById(clientId)
        if (opt.isPresent) {
            return opt.get().reports
        }
        throw IllegalStateException("I can't find client with id=$clientId")
    }
}
